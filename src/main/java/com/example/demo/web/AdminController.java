package com.example.demo.web;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.api.R;
import com.example.demo.common.MyConstants;
import com.example.demo.common.MyConstants.sellStatus;
import com.example.demo.entity.CategoryH;
import com.example.demo.entity.OrderH;
import com.example.demo.entity.ProductH;
import com.example.demo.entity.UserH;
import com.example.demo.service.ICategoryHService;
import com.example.demo.service.IOrderHService;
import com.example.demo.service.IProductHService;
import com.example.demo.service.IUserHService;
import com.example.demo.service.impl.CategoryHServiceImpl;

import com.example.demo.vo.MyOrderVO;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.demo.vo.HomeVO;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/templates/admin")
public class AdminController {

  @Autowired
  private ICategoryHService categoryHService;
  @Autowired
  private IUserHService userHService;
  @Autowired
  private IProductHService productHService;
  @Autowired
  private IOrderHService orderHService;


  /**
   * 首页
   */
  @GetMapping("/home")
  public R home() {
    List<UserH> userHList = userHService.list();
    List<CategoryH> categoryHList = categoryHService.getCategoryList(sellStatus.onSell);
    List<ProductH> productHList = productHService.getHotProduct();
    List<OrderH> orderHList = orderHService.listOrder(MyConstants.orderStatus.finished);
    HomeVO homeVO = new HomeVO();
    homeVO.setUserNumber(userHList.size());
    homeVO.setCategoryNumber(categoryHList.size());
    homeVO.setProductNumber(productHList.size());
    homeVO.setSellNumber(orderHList.size());
    return R.ok(homeVO);
  }

  /**
   * 添加分类
   */
  @PostMapping("/saveCategory")
  public R saveCategory(String name, String realName) {
    CategoryH categoryH = new CategoryH();
    categoryH.setId(IdWorker.getId());
    categoryH.setCreateTime(new Date());
    categoryH.setSortOrder(1);
    categoryH.setStatus(sellStatus.onSell);
    categoryH.setName(name);
    categoryH.setRealName(realName);

    boolean flag = categoryHService.saveCategory(categoryH);
    if (flag) {
      return R.ok("添加分类成功");
    } else {
      return R.failed("添加分类失败");
    }
  }

  /**
   * 查看分类列表
   */
  @GetMapping("/listCategory")
  public R listCategory() {
    List<CategoryH> categoryHList = categoryHService.getCategoryList(sellStatus.onSell);
    return R.ok(categoryHList);
  }

  /**
   * 删除分类
   */
  @PostMapping("/deleteCategory")
  public R deleteCategory(Long id) {
    return categoryHService.deleteCategory(id) ? R.ok("删除成功") : R.failed("删除失败");
  }

  /**
   * 上传商品
   */
  @PostMapping("/savaProduct")
  public R saveProduct(@RequestParam(value = "file") MultipartFile file, String name,
      Long categoryId, String brand, String detail, Double price, Double oldPrice,
      String content, HttpSession session) throws FileNotFoundException {

    ProductH productH = new ProductH();

    if (file.isEmpty() == false) {
      String fileName = System.currentTimeMillis() + file.getOriginalFilename();
      String filePath = ResourceUtils
          .getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "templates/product").getPath();
      try {
        FileUtil.writeBytes(file.getBytes(), filePath + "/" + fileName);
      } catch (IOException e) {
        return R.ok("上传照片失败");
      }
      productH.setMainImage("product/" + fileName);
    } else {
      return R.ok("请上传照片");
    }

    productH.setId(IdWorker.getId());
    productH.setCategoryId(categoryId);
    productH.setName(name);
    productH.setBrand(brand);
    productH.setCreateId(Long.valueOf(session.getAttribute("userId").toString()));
    productH.setDetail(detail);
    productH.setPrice(price);
    productH.setStock(RandomUtil.randomInt(1, 10));
    productH.setOldPrice(oldPrice);
    productH.setStatus(sellStatus.onSell);
    productH.setCreateTime(new Date());
    productH.setOldPrice(oldPrice);
    productH.setContent(content);
    boolean flag = productHService.saveProduct(productH);
    if (flag) {
      return R.ok("上传成功");
    } else {
      return R.failed("上传失败");
    }
  }

  /**
   * 下架商品
   */
  @PostMapping("/deleteProduct")
  public R deleteProduct(Long id) {
    return productHService.deleteProduct(id) ? R.ok("下架成功") : R.failed("下架失败");
  }

  /**
   * 获取商品列表
   */
  @GetMapping("/listProduct")
  public R listProduct(HttpSession session) {
    return R.ok(productHService
        .listProductByCreateId(Long.valueOf(session.getAttribute("userId").toString())));
  }

  /**
   * 获取订单列表
   */
  @GetMapping("/listOrder")
  public R listOrder(HttpSession session) {
    Long userId = Long.valueOf(session.getAttribute("userId").toString());
    List<OrderH> orderHList = orderHService.listOrder(null);
    List<MyOrderVO> orderVOS = new ArrayList<>();
    for (OrderH orderH : orderHList) {
      List<Long> categoryIds = JSONObject.parseArray(orderH.getPostage(), Long.class);
      for (Long categoryId : categoryIds) {
        MyOrderVO myOrderVO = new MyOrderVO();
        ProductH productH = productHService.selProductById(categoryId);
        if(ObjectUtil.equal(productH.getCreateId(),userId)){
          myOrderVO.setProductId(productH.getId());
          myOrderVO.setProductName(productH.getName());
          myOrderVO.setProductPhoto(productH.getMainImage());
          myOrderVO.setProductPrice(productH.getPrice());
          myOrderVO.setBuyerName(orderH.getBuyerName());
          myOrderVO.setBuyerPhone(orderH.getBuyerPhone());
          myOrderVO.setAddress(orderH.getAddress());
          myOrderVO.setPayment(orderH.getPayment());
          myOrderVO.setPaymentTime(orderH.getPaymentTime());
          myOrderVO.setStatus(orderH.getStatus());
          myOrderVO.setOrderId(orderH.getId());
          orderVOS.add(myOrderVO);
        }

      }
    }
    return R.ok(orderVOS);
  }


  /**
   * 修改订单状态
   */
  @PostMapping("/updateOrder")
  public R updateOrder(Long id, Integer status) {
    return orderHService.updateOrder(id, status) ? R.ok("修改成功") : R.failed("修改失败");
  }
}

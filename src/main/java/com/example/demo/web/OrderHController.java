package com.example.demo.web;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.api.R;
import com.example.demo.entity.OrderH;
import com.example.demo.entity.ProductH;
import com.example.demo.service.ICartHService;
import com.example.demo.service.IOrderHService;
import com.example.demo.service.IProductHService;
import com.example.demo.vo.MyOrderVO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p> 订单表 前端控制器 </p>
 *
 * @author ChenRui
 * @since 2019-01-15
 */
@RestController
@RequestMapping("/templates/order")
public class OrderHController {

  @Autowired
  private IOrderHService orderHService;
  @Autowired
  private IProductHService productHService;
  @Autowired
  private ICartHService cartHService;

  /**
   * 购买商品
   *
   * @param productIds 商量IDs
   * @param address 地址
   * @param userId 用户ID
   */
  @PostMapping("/addOrder")
  public R addOrder(String[] productIds, String address, String buyerName, Long buyerPhone,
      Long userId) {
    List<ProductH> productHList = productHService
        .list(new LambdaQueryWrapper<ProductH>().in(ProductH::getId, productIds));
   Double payment = 0D;

    List<Long> postageList = new ArrayList<>();
    for (ProductH productH : productHList) {
      payment += productH.getPrice();

      postageList.add(productH.getId());
    }
    Date date = new Date();
    OrderH orderH = new OrderH();
    orderH.setId(IdWorker.getId());
    orderH.setUserId(userId);
    orderH.setPayment(payment);
    orderH.setPostage(JSONObject.toJSONString(postageList));
    orderH.setPaymentTime(date);
    orderH.setCreateTime(date);
    orderH.setAddress(address);
    orderH.setStatus(0);
    orderH.setBuyerName(buyerName);
    orderH.setBuyerPhone(buyerPhone);
    boolean flag = orderHService.save(orderH);
    if (flag) {
      for (String productId : productIds) {
        cartHService.delCart(userId, Long.parseLong(productId));
      }
      return R.ok("");
    } else {
      return R.failed("");
    }
  }

  /**
   * 查看订单
   *
   * @param userId 用户ID
   */
  @GetMapping("/selOrder")
  public R selOrder(Long userId) {
    List<OrderH> orderHList = orderHService.selOrder(userId);
    List<ProductH> productHList = new ArrayList<>();
    for (OrderH orderH : orderHList) {
      List<Long> categoryIds = JSONObject.parseArray(orderH.getPostage(), Long.class);
      for (Long categoryId : categoryIds) {
        productHList.add(productHService.selProductById(categoryId));
      }
    }
    List<MyOrderVO> orderVOS = new ArrayList<>();
    for (ProductH productH : productHList) {
      MyOrderVO myOrderVO = new MyOrderVO();
      myOrderVO.setProductId(productH.getId());
      myOrderVO.setProductName(productH.getName());
      myOrderVO.setProductPhoto(productH.getMainImage());
      myOrderVO.setProductPrice(productH.getPrice());

      orderVOS.add(myOrderVO);
    }
    return R.ok(orderVOS);
  }
}

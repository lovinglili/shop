package com.example.demo.web;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.MyConstants.sellStatus;
import com.example.demo.common.MyConstants.sort;
import com.example.demo.entity.ProductH;
import com.example.demo.service.IProductHService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p> 商品表 前端控制器 </p>
 *
 * @author ChenRui
 * @since 2019-01-15
 */
@RestController
@RequestMapping("/templates/product")
public class ProductHController {

  @Autowired
  private IProductHService iProductHService;

  /**
   * 获取最新上架的商品 （时间倒序的8个商品）
   */
  @GetMapping("/getOnSellProduct")
  public R getOnSellProduct() {
    List<ProductH> productHList = iProductHService.getProductList(sellStatus.onSell, sort.desc);
    return R.ok(productHList);
  }

  /**
   * 通过ID查询商品信息
   *
   * @param id 商品ID
   */
  @PostMapping("/selProductById")
  public R selProductById(Long id) {
    ProductH productH = iProductHService.selProductById(id);
    return R.ok(productH);
  }

  /**
   * 通过分类ID查找商品列表
   *
   * @param categoryId 分类ID
   */
  @PostMapping("/selProductByCategoryId")
  public R selProductByCategoryId(Long categoryId, Long productId) {

    List<ProductH> productHList = iProductHService.selProductByCategoryId(categoryId);
    if (ObjectUtils.isNotEmpty(productId)) {
      productHList = productHList.stream().filter(it -> it.getId() - productId != 0)
          .collect(Collectors.toList());
    }
    return R.ok(productHList);
  }

  /**
   * 获取关注度最多的商品
   */
  @GetMapping("/getHotProduct")
  public R getHotProduct() {
    List<ProductH> productHList = iProductHService.getHotProduct();
    return R.ok(productHList);
  }

  /**
   * 分页查找商品列表
   */
  @PostMapping("/pageProduct")
  public R pageProduct(Integer current, Long categoryId, String productName) {
    Page page = new Page();
    if (ObjectUtils.isNotEmpty(current)) {
      page.setCurrent(current);
    }
    IPage<ProductH> productHIPage = iProductHService.pageProduct(page, categoryId, productName);
    return R.ok(productHIPage);
  }

}

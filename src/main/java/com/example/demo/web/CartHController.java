package com.example.demo.web;


import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.api.R;
import com.example.demo.entity.CartH;
import com.example.demo.entity.ProductH;
import com.example.demo.service.ICartHService;
import com.example.demo.service.IProductHService;
import com.example.demo.vo.CartProductVO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p> 前端控制器 </p>
 *
 * @author ChenRui
 * @since 2019-04-01
 */
@RestController
@RequestMapping("/templates/cart")
public class CartHController {

  @Autowired
  private ICartHService cartHService;
  @Autowired
  private IProductHService productHService;

  /**
   * 添加购物车
   *
   * @param userId 用户ID
   * @param productId 商品ID
   */
  @PostMapping("/addCart")
  public R addCart(Long userId, Long productId) {
    CartH cartH = new CartH();
    cartH.setId(IdWorker.getId());
    cartH.setProductId(productId);
    cartH.setUserId(userId);
    cartH.setCreateTime(new Date());
    return R.ok(cartHService.saveCart(cartH));
  }

  /**
   * 查看购物车
   *
   * @param userId 用户ID
   */
  @GetMapping("/selCart")
  public R selCart(Long userId) {
    List<CartH> cartHList = cartHService.selCartH(userId);

    List<CartProductVO> cartProductVOList = cartHList.stream().map(it -> {

      CartProductVO cartProductVO = new CartProductVO();
      cartProductVO.setProductId(it.getProductId());
      ProductH product = productHService.getById(it.getProductId());
      cartProductVO.setProductImg(product.getMainImage());
      cartProductVO.setProductName(product.getName());
      cartProductVO.setProductPrice(product.getPrice());
      return cartProductVO;
    }).collect(Collectors.toList());
    return R.ok(cartProductVOList);
  }

  /**
   * 删除购物车
   *
   * @param productId 商品ID
   * @param userId 用户ID
   */
  @PostMapping("/delCart")
  public R delCart(Long productId, Long userId) {
    boolean flag = cartHService.delCart(userId, productId);
    if (flag) {
      return R.ok("");
    } else {
      return R.failed("");
    }
  }
}

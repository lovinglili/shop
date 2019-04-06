package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.CartH;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ChenRui
 * @since 2019-04-01
 */
public interface ICartHService extends IService<CartH> {

  /**
   * 添加购物车
   * @param cartH
   * @return
   */
  boolean saveCart(CartH cartH);

  /**
   * 查看购物车
   * @param userId 用户ID
   */
  List<CartH> selCartH(Long userId);

  /**
   * 删除购物车
   * @param userId 用户ID
   * @param productId 商品ID
   * @return
   */
  boolean delCart(Long userId,Long productId);
}

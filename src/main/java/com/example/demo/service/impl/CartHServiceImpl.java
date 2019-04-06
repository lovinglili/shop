package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.CartH;
import com.example.demo.mapper.CartHMapper;
import com.example.demo.service.ICartHService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * <p> 服务实现类 </p>
 *
 * @author ChenRui
 * @since 2019-04-01
 */
@Service
public class CartHServiceImpl extends ServiceImpl<CartHMapper, CartH> implements ICartHService {

  @Override
  public boolean saveCart(CartH cartH) {
    return this.save(cartH);
  }

  @Override
  public List<CartH> selCartH(Long userId) {
    return this.list(new LambdaQueryWrapper<CartH>().eq(CartH::getUserId, userId));
  }

  @Override
  public boolean delCart(Long userId, Long productId) {
    return this.remove(new LambdaQueryWrapper<CartH>().eq(CartH::getUserId,userId).eq(CartH::getProductId,productId));
  }
}

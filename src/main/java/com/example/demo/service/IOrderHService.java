package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.OrderH;
import java.util.List;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author ChenRui
 * @since 2019-01-15
 */
public interface IOrderHService extends IService<OrderH> {

  /**
   * 查看订单
   * @param userId 用户ID
   * @return
   */
  List<OrderH> selOrder(Long userId);

  /**
   * 查看订单
   * @return
   */
  List<OrderH> listOrder(Integer status);

  /**
   * 修改订单状态
   * @param id
   * @param status
   * @return
   */
  boolean updateOrder(Long id,Integer status);
}

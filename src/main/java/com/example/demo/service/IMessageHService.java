package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.MessageH;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ChenRui
 * @since 2019-01-21
 */
public interface IMessageHService extends IService<MessageH> {

  /**
   * 添加留言
   * @param messageH
   * @return
   */
  boolean saveMessage(MessageH messageH);

  /**
   * 查看某一商品的留言
   * @param productId 商品ID
   * @return
   */
  List<MessageH> selMessageByProductId(Long productId);
}

package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.MessageH;
import com.example.demo.mapper.MessageHMapper;
import com.example.demo.service.IMessageHService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ChenRui
 * @since 2019-04-01
 */
@Service
public class MessageHServiceImpl extends ServiceImpl<MessageHMapper, MessageH> implements IMessageHService {

  @Override
  public boolean saveMessage(MessageH messageH) {
    return this.save(messageH);
  }

  @Override
  public List<MessageH> selMessageByProductId(Long productId) {
    return this.list(new LambdaQueryWrapper<MessageH>().eq(MessageH::getProductId,productId));
  }
}

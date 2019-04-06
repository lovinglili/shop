package com.example.demo.web;


import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.api.R;
import com.example.demo.entity.MessageH;
import com.example.demo.service.IMessageHService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ChenRui
 * @since 2019-01-21
 */
@RestController
@RequestMapping("/templates/message")
public class MessageHController  {
  @Autowired
  private IMessageHService iMessageHService;

  /**
   * 对商品进行留言
   * @param userId 用户ID
   * @param productId 商品ID
   * @param message 留言内容
   * @return
   */
  @PostMapping("/saveMessage")
  public R saveMessage(Long userId,Long productId,String message){
    MessageH messageH = new MessageH();
    messageH.setId(IdWorker.getId());
    messageH.setUserId(userId);
    messageH.setProductId(productId);
    messageH.setDate(new Date());
    messageH.setMessage(message);

    boolean flag = iMessageHService.saveMessage(messageH);
    return R.ok(flag);
  }

  /**
   * 查看某一商品的留言
   * @param productId 商品ID
   * @return
   */
  @GetMapping("/selMessage")
  public R selMessage(Long productId){
    return R.ok(iMessageHService.selMessageByProductId(productId));
  }
}

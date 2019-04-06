package com.example.demo.web;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.api.R;
import com.example.demo.common.IsPhone;
import com.example.demo.entity.UserH;
import com.example.demo.service.IUserHService;
import java.util.Date;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p> 用户表 前端控制器 </p>
 *
 * @author ChenRui
 * @since 2019-01-15
 */
@RestController
@RequestMapping("/templates/user")
public class UserHController {

  @Autowired
  private IUserHService iUserHService;

  /**
   * 用户注册
   *
   * @param userName 用户名
   * @param password 密码
   * @param phone 电话号
   * @param question 问题
   * @param answer 答案
   */
  @PostMapping("/register")
  public R register(String userName, String password, String phone, String question,
      String answer, Integer role) {
    UserH userH = iUserHService.getUserByUserName(userName);
    if (ObjectUtils.isEmpty(userH)) {
      if (!IsPhone.isPhone(phone)) {
        return R.failed("手机号校验失败");
      }
      userH = new UserH();
      userH.setId(IdWorker.getId());
      userH.setUsername(userName);
      userH.setPassword(SecureUtil.md5(password));
      userH.setPhone(phone);
      userH.setQuestion(question);
      userH.setAnswer(answer);
      userH.setCreateTime(new Date());
      userH.setRole(role);

      boolean save = iUserHService.register(userH);
      if (save) {
        return R.ok("注册成功");
      } else {
        return R.failed("注册失败");
      }
    } else {
      return R.failed("用户名已存在");
    }
  }

  /**
   * 用户登录
   *
   * @param userName 用户名
   * @param password 密码
   */
  @PostMapping("/login")
  public R login(HttpSession session, String userName, String password, Integer role) {
    UserH userH = iUserHService.login(userName, password, role);
    if (ObjectUtils.isNotEmpty(userH)) {
      session.setAttribute("userId", userH.getId());
      return R.ok(userH);
    } else {
      return R.failed("用户名或密码错误");
    }
  }

  /**
   * 修改密码
   *
   * @param password1 密码1
   * @param password2 密码2
   * @param userId 用户ID
   * @param answer 密保问题
   */
  @PostMapping("/update")
  public R update(String password1, String password2, Long userId, String answer) {
    if (!ObjectUtil.equal(password1, password2)) {
      return R.failed("两次密码不同");
    } else {
      boolean flag = iUserHService.updatePassword(userId, answer, password2);
      if (flag) {
        return R.ok("修改密码成功");
      } else {
        return R.failed("密保问题回答错误！");
      }
    }
  }

  /**
   * 获取用户密保问题
   *
   * @param userId 用户id
   */
  @GetMapping("/getQuestion")
  public R getQuestion(Long userId) {
    String question = iUserHService.getUserInfo(userId).getQuestion();
    return R.ok(question);
  }
}

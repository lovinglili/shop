package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.UserH;

/**
 * <p> 用户表 服务类 </p>
 *
 * @author ChenRui
 * @since 2019-01-15
 */
public interface IUserHService extends IService<UserH> {

  /**
   * 登录
   *
   * @param username 用户名
   * @param password 密码
   */
  UserH login(String username, String password, Integer role);

  /**
   * 注册
   */
  boolean register(UserH userH);

  /**
   * 修改密码
   *
   * @param id 用户ID
   * @param answer 答案
   * @param newPassword 新密码
   */
  boolean updatePassword(Long id, String answer, String newPassword);

  /**
   * 通过用户名查询
   */
  UserH getUserByUserName(String userName);

  /**
   * 获取用户信息
   *
   * @param userId 用户ID
   */
  UserH getUserInfo(Long userId);
}

package com.example.demo.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.UserH;
import com.example.demo.mapper.UserHMapper;
import com.example.demo.service.IUserHService;
import java.util.Date;
import org.springframework.stereotype.Service;

/**
 * <p> 用户表 服务实现类 </p>
 *
 * @author ChenRui
 * @since 2019-04-01
 */
@Service
public class UserHServiceImpl extends ServiceImpl<UserHMapper, UserH> implements IUserHService {

  @Override
  public UserH login(String username, String password, Integer role) {
    //对密码MD5加密
    password = SecureUtil.md5(password);
    //通过用户密码查询
    UserH userH = this.getOne(new LambdaQueryWrapper<UserH>().eq(UserH::getUsername, username)
        .eq(UserH::getPassword, password).eq(UserH::getRole, role));
    return userH;
  }

  @Override
  public boolean register(UserH userH) {
    return this.save(userH);
  }

  @Override
  public boolean updatePassword(Long id, String answer, String newPassword) {
    UserH userH = this.getById(id);
    //密码答案回答正确
    if (answer.equals(userH.getAnswer())) {
      userH.setPassword(SecureUtil.md5(newPassword));
      userH.setUpdateTime(new Date());
      return this.updateById(userH);
    } else {
      return false;
    }
  }

  @Override
  public UserH getUserByUserName(String userName) {
    return this.getOne(new LambdaQueryWrapper<UserH>().eq(UserH::getUsername, userName));
  }

  @Override
  public UserH getUserInfo(Long userId) {
    return this.getById(userId);
  }
}

package com.example.demo.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.OrderH;
import com.example.demo.mapper.OrderHMapper;
import com.example.demo.service.IOrderHService;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author ChenRui
 * @since 2019-04-01
 */
@Service
public class OrderHServiceImpl extends ServiceImpl<OrderHMapper, OrderH> implements IOrderHService {

    /**
     * 查看订单
     *
     * @param userId 用户ID
     * @return
     */
    @Override
    public List<OrderH> selOrder(Long userId) {
        List<OrderH> orderHList = this.list(new LambdaQueryWrapper<OrderH>().eq(ObjectUtils.isNotEmpty(userId), OrderH::getUserId, userId));
        return orderHList;
    }

    @Override
    public List<OrderH> listOrder(Integer status) {
        return this.list(new LambdaQueryWrapper<OrderH>().eq(ObjectUtil.isNotNull(status), OrderH::getStatus, status).orderByDesc(OrderH::getCreateTime));
    }

    @Override
    public boolean updateOrder(Long id, Integer status) {
        OrderH orderH = this.getById(id);
        orderH.setStatus(status);
        return this.updateById(orderH);
    }
}

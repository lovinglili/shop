package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.common.MyConstants;
import com.example.demo.common.MyConstants.sellStatus;
import com.example.demo.entity.CategoryH;
import com.example.demo.mapper.CategoryHMapper;
import com.example.demo.service.ICategoryHService;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品分类表 服务实现类
 * </p>
 *
 * @author ChenRui
 * @since 2019-04-01
 */
@Service
public class CategoryHServiceImpl extends ServiceImpl<CategoryHMapper, CategoryH> implements ICategoryHService {

    @Override
    public List<CategoryH> getCategoryList(Integer status) {
        return this.list(new LambdaQueryWrapper<CategoryH>().eq(CategoryH::getStatus, status));
    }

    @Override
    public boolean saveCategory(CategoryH categoryH) {
        return this.save(categoryH);
    }

    @Override
    public boolean deleteCategory(Long id) {
        CategoryH categoryH = this.getById(id);
        categoryH.setStatus(sellStatus.lowerShelf);
        return this.updateById(categoryH);
    }
}

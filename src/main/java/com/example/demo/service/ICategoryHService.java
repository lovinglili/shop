package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.CategoryH;
import java.util.List;

/**
 * <p>
 * 商品分类表 服务类
 * </p>
 *
 * @author ChenRui
 * @since 2019-01-15
 */

public interface ICategoryHService extends IService<CategoryH> {

  /**
   * 获取分类列表
   * @return
   */
  List<CategoryH> getCategoryList(Integer status);

  /**
   * 添加分类
   * @param categoryH
   * @return
   */
  boolean saveCategory(CategoryH categoryH);

  /**
   * 删除分类
   * @param id
   * @return
   */
  boolean deleteCategory(Long id);
}

package com.example.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.ProductH;
import java.util.List;

/**
 * <p> 商品表 服务类 </p>
 *
 * @author ChenRui
 * @since 2019-01-15
 */
public interface IProductHService extends IService<ProductH> {

  /**
   * 获取某种状态下的商品列表
   *
   * @param status 1-在售 2-下架 3-删除
   */
  List<ProductH> getProductList(Integer status, String sort);

  /**
   * 通过ID查询商品信息
   *
   * @param id 商品ID
   */
  ProductH selProductById(Long id);

  /**
   * 通过分类ID查找商品列表
   *
   * @param categoryId 分类ID
   */
  List<ProductH> selProductByCategoryId(Long categoryId);

  /**
   * 获取关注度的商品列表
   */
  List<ProductH> getHotProduct();

  /**
   * 分页查找商品
   */
  IPage<ProductH> pageProduct(Page page, Long categoryId, String productName);

  /**
   * 上传商品
   */
  boolean saveProduct(ProductH productH);

  /**
   * 删除商品
   */
  boolean deleteProduct(Long id);

  /**
   * 通过创建人ID获取
   */
  List<ProductH> listProductByCreateId(Long createId);
}

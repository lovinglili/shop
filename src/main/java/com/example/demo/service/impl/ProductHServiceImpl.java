package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.common.MyConstants;
import com.example.demo.common.MyConstants.orderStatus;
import com.example.demo.common.MyConstants.sellStatus;
import com.example.demo.common.MyConstants.sort;
import com.example.demo.entity.ProductH;
import com.example.demo.mapper.ProductHMapper;
import com.example.demo.service.IProductHService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * <p> 商品表 服务实现类 </p>
 *
 * @author ChenRui
 * @since 2019-04-01
 */
@Service
public class ProductHServiceImpl extends ServiceImpl<ProductHMapper, ProductH> implements
    IProductHService {

  @Override
  public List<ProductH> getProductList(Integer status, String sort) {
    boolean sortFlag = false;
    boolean sortOrderFlag = false;
    if (StringUtils.isNotEmpty(sort)) {
      sortFlag = true;
      if (sort.equals(MyConstants.sort.asc)) {
        sortOrderFlag = true;
      }
    }
    return this.list(new LambdaQueryWrapper<ProductH>().eq(ProductH::getStatus, status)
        .orderBy(sortFlag, sortOrderFlag, ProductH::getCreateTime).last("limit 8"));
  }

  @Override
  public ProductH selProductById(Long id) {
    return this.getById(id);
  }

  @Override
  public List<ProductH> selProductByCategoryId(Long categoryId) {
    return this.list(new LambdaQueryWrapper<ProductH>()
        .eq(ObjectUtils.isNotEmpty(categoryId), ProductH::getCategoryId, categoryId)
        .eq(ProductH::getStatus,
            sellStatus.onSell).orderBy(true, false, ProductH::getCreateTime));
  }

  @Override
  public List<ProductH> getHotProduct() {
    return this.list(new LambdaQueryWrapper<ProductH>().eq(ProductH::getStatus,
        sellStatus.onSell).orderBy(true, false, ProductH::getStock));
  }

  @Override
  public IPage<ProductH> pageProduct(Page page, Long categoryId, String productName) {
    boolean flag = false;
    if (ObjectUtils.isNotEmpty(categoryId)) {
      flag = true;
    }
    page.setSize(9L);
    IPage<ProductH> productHPage = this
        .page(page, new LambdaQueryWrapper<ProductH>().eq(flag, ProductH::getCategoryId, categoryId)
            .eq(ProductH::getStatus,
                sellStatus.onSell)
            .like(StringUtils.isNotEmpty(productName), ProductH::getName, productName)
            .orderBy(true, false, ProductH::getCreateTime));
    return productHPage;
  }

  @Override
  public boolean saveProduct(ProductH productH) {
    return this.save(productH);
  }

  @Override
  public boolean deleteProduct(Long id) {
    ProductH productH = this.getById(id);
    productH.setStatus(sellStatus.lowerShelf);
    return this.updateById(productH);
  }

  @Override
  public List<ProductH> listProductByCreateId(Long createId) {
    return this.list(new LambdaQueryWrapper<ProductH>().eq(ProductH::getCreateId, createId)
        .eq(ProductH::getStatus,
            sellStatus.onSell));
  }
}

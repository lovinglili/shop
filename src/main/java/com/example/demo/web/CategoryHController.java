package com.example.demo.web;


import com.baomidou.mybatisplus.extension.api.R;
import com.example.demo.common.MyConstants;
import com.example.demo.common.MyConstants.sellStatus;
import com.example.demo.entity.CategoryH;
import com.example.demo.service.ICategoryHService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 商品分类表 前端控制器
 * </p>
 *
 * @author ChenRui
 * @since 2019-01-15
 */
@RestController
@RequestMapping("/templates/category")
public class CategoryHController {
  @Autowired
  private ICategoryHService iCategoryHService;

  /**
   * 获取分类列表
   * @return
   */
  @GetMapping("/getCategoryList")
  public R getCategoryList(){
    List<CategoryH> categoryHList = iCategoryHService.getCategoryList(sellStatus.onSell);
    return R.ok(categoryHList);
  }
}

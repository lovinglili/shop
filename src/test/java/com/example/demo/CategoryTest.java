package com.example.demo;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.example.demo.common.Category;
import com.example.demo.entity.CategoryH;
import com.example.demo.service.ICategoryHService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryTest {
  @Autowired
  private ICategoryHService iCategoryHService;
  /**
   * 添加产品分类
   */
  @Test
  public void saveCategoryTest(){
    List<CategoryH> categoryHList = new ArrayList<>();
    for(int i=0;i<Category.values().length;i++){
      CategoryH categoryH = new CategoryH();
      categoryH.setId(IdWorker.getId());
      categoryH.setStatus(1);
      categoryH.setSortOrder(1);
      categoryH.setCreateTime(new Date());
      categoryHList.add(categoryH);
    }
    iCategoryHService.saveBatch(categoryHList);
  }
}

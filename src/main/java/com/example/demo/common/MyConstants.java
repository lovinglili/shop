package com.example.demo.common;

public interface MyConstants {

  /**
   * 销售状态
   */
  interface sellStatus{

    /**
     * 在售
     */
    int onSell = 1;
    /**
     * 下架
     */
    int lowerShelf = 0;
    /**
     * 删除
     */
    int delete = -1;
  }

  /**
   * 排序方式
   */
  interface sort{

    /**
     * 倒序
     */
    String desc = "desc";
    /**
     * 正序
     */
    String asc = "asc";
  }

  interface orderStatus{
    /**
     * 取消
     */
    Integer delete = -1;
    /**
     * 等待
     */
    Integer wait = 0;
    /**
     * 已发货
     */
    Integer send = 1;
    /**
     * 完成
     */
    Integer finished = 2;
  }
}

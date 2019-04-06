package com.example.demo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @auther ChenRui
 * @date 2019/3/18
 */
@Data
public class HomeVO implements Serializable {
    /**
     * 用户数量
     */
    private Integer userNumber;
    /**
     * 分类数量
     */
    private Integer categoryNumber;
    /**
     * 商品数量
     */
    private Integer productNumber;
    /**
     * 已售数量
     */
    private Integer sellNumber;
}

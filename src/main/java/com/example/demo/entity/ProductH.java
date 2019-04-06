package com.example.demo.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author ChenRui
 * @since 2019-01-15
 */
@Data
@Accessors(chain = true)
@TableName("product_h")
public class ProductH implements Serializable{

    private static final long serialVersionUID = 1L;
    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 分类id,对应mmall_category表的主键
     */
    @TableField("category_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long categoryId;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 产品主图,url相对地址
     */
    @TableField("main_image")
    private String mainImage;

    /**
     * 图片地址,json格式,扩展用
     */
    @TableField("sub_images")
    private String subImages;

    /**
     * 商品详情
     */
    private String detail;

    /**
     * 价格,单位-元保留两位小数
     */
    private Double price;

    /**
     * 库存数量
     */
    private Integer stock;

    /**
     * 商品状态.1-在售 2-下架 3-删除
     */
    private Integer status;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 原价
     */
    @TableField("old_price")
    private Double oldPrice;

    private String content;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long createId;

}

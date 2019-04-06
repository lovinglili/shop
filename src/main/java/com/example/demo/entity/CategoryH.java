package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品分类表
 * </p>
 *
 * @author ChenRui
 * @since 2019-01-15
 */
@Data
@Accessors(chain = true)
@TableName("category_h")
public class CategoryH  {

    private static final long serialVersionUID = 1L;
    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 父类别id当id=0时说明是根节点,一级类别
     */
    @TableField("parent_id")
    private Integer parentId;

    /**
     * 类别名称
     */
    private String name;
    @TableField("real_name")
    private String realName;

    /**
     * 类别状态1-正常,2-已废弃
     */
    private Integer status;

    /**
     * 排序编号,同类展示顺序,数值相等则自然排序
     */
    @TableField("sort_order")
    private Integer sortOrder;

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

}

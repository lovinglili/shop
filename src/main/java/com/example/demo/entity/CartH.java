package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author ChenRui
 * @since 2019-04-01
 */
@Data
@Accessors(chain = true)
@TableName("cart_h")
public class CartH  {

    private static final long serialVersionUID = 1L;
    @TableId
    private Long id;
    @TableField("product_id")
    private Long productId;

    @TableField("user_id")
    private Long userId;

    @TableField("create_time")
    private Date createTime;

}

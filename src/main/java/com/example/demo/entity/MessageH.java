package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author ChenRui
 * @since 2019-01-21
 */
@Data
@Accessors(chain = true)
@TableName("message_h")
public class MessageH  {

    private static final long serialVersionUID = 1L;
    @TableId
    private Long id;
    @TableField("user_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;
    @TableField("product_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long productId;

    private String message;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;



}

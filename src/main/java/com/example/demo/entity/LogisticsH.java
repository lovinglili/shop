package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 物流表
 * </p>
 *
 * @author ChenRui
 * @since 2019-01-15
 */
@Data
@Accessors(chain = true)
@TableName("logistics_h")
public class LogisticsH  {

    private static final long serialVersionUID = 1L;
    @TableId
    private Long id;
    /**
     * 用户id
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 收货姓名
     */
    @TableField("receiver_name")
    private String receiverName;

    /**
     * 收货固定电话
     */
    @TableField("receiver_phone")
    private String receiverPhone;

    /**
     * 收货移动电话
     */
    @TableField("receiver_mobile")
    private String receiverMobile;

    /**
     * 省份
     */
    @TableField("receiver_province")
    private String receiverProvince;

    /**
     * 城市
     */
    @TableField("receiver_city")
    private String receiverCity;

    /**
     * 区/县
     */
    @TableField("receiver_district")
    private String receiverDistrict;

    /**
     * 详细地址
     */
    @TableField("receiver_address")
    private String receiverAddress;

    /**
     * 邮编
     */
    @TableField("receiver_zip")
    private String receiverZip;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;



}

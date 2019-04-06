package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p> 订单表 </p>
 *
 * @author ChenRui
 * @since 2019-01-15
 */
@Data
@Accessors(chain = true)
@TableName("order_h")
public class OrderH {

  private static final long serialVersionUID = 1L;
  @TableId
  @JsonSerialize(using = ToStringSerializer.class)
  private Long id;
  /**
   * 用户id
   */
  @TableField("user_id")
  private Long userId;

  @TableField("logistics_id")
  private Long logisticsId;

  /**
   * 实际付款金额,单位是元,保留两位小数
   */
  private Double payment;

  /**
   * 运费,单位是元
   */
  private String postage;

  private String address;

  /**
   * 订单状态:0-已取消-10-未付款，20-已付款，40-已发货，50-交易成功，60-交易关闭
   */
  private Integer status;

  /**
   * 支付时间
   */
  @TableField("payment_time")
  private Date paymentTime;

  /**
   * 发货时间
   */
  @TableField("send_time")
  private Date sendTime;

  /**
   * 交易完成时间
   */
  @TableField("end_time")
  private Date endTime;

  /**
   * 交易关闭时间
   */
  @TableField("close_time")
  private Date closeTime;

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

  private String buyerName;

  private Long buyerPhone;


}

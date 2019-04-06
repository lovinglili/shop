package com.example.demo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class MyOrderVO implements Serializable{

  /**
   * 商品ID
   */
  @JsonSerialize(using = ToStringSerializer.class)
  private Long productId;
  /**
   * 商品名
   */
  private String productName;
  /**
   * 商品照片
   */
  private String productPhoto;
  /**
   * 商品价格
   */
  private Double productPrice;
  private String buyerName;
  @JsonSerialize(using = ToStringSerializer.class)
  private Long buyerPhone;
  private String address;
  private Double payment;
  private Date paymentTime;
  private Integer status;
  @JsonSerialize(using = ToStringSerializer.class)
  private Long orderId;
}

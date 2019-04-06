package com.example.demo.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class CartProductVO implements Serializable{
  @JsonSerialize(using = ToStringSerializer.class)
  private Long productId;
  private String productImg;
  private String productName;
  private Double productPrice;

}

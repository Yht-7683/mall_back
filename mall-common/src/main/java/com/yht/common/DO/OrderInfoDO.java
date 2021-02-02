package com.yht.common.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单实体类
 */
@Data
@TableName("order_info")
public class OrderInfoDO {
    /**
     * 订单编号
     */
    private String orderId;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户名
     */
    @TableField(exist = false)
    private String username;
    /**
     * 下单时间
      */
    private Date createTime;
    /**
     * 支付状态：0未支付 1已经支付
     */
    private Integer pay;
    /**
     * 商品总价格
     */
    private BigDecimal totalPrice;
    /**
     * 运费
     */
    private BigDecimal freight;
    /**
     * 订单状态
     */
    private Integer status;
    /**
     * 其他说明
     */
    private String other;


}

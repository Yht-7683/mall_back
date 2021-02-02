package com.yht.common.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 订单商品实体类
 */
@Data
@TableName("order_product")
public class OrderProductDO {

    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 订单编号
     */
    private String orderId;

    private Long skuId;
    /**
     * 商品名称，下单是的名称，后面改名了不算！ヾ(｡｀Д´｡)ﾉ彡
     */
    private String goodsName;
    /**
     * 下单商品数量
     */
    private Long goodsAmount;
    /**
     * 购买时的单价
     */
    private BigDecimal goodsPrice;
}

package com.yht.common.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

/**
 * 商品库存类
 */
@Data
@TableName("goods_sku")
public class GoodsStockDO {
    /**
     * 库存id
     */
    @TableId(type = IdType.AUTO)
    private Long skuId;
    /**
     * 商品id
     */
    private Long goodsId;
    /**
     * 商品名
     */
    @TableField(exist = false)
    private String name;
    /**
     * 商品数量
     */
    @NotBlank(message = "数量不能为空")
    private Long amount;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 状态  0：下架   1：正常上架
     */
    private Integer status;
}

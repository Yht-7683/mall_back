package com.yht.common.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

/**
 * 购物车实体类
 */
@Data
@TableName("order_cart")
public class CartDO {
    /**
     * 购物车id
     */
    @TableId(type = IdType.AUTO)
    private Long cartId;
    /**
     * 用户id
     */
    @NotBlank(message = "用户id不能为空")
    private Long userId;
    /**
     * 库存id
     */
    @NotBlank(message = "skuId不能为空")
    private Long skuId;
    /**
     * 数量
     */
    @NotBlank(message = "数量不能为空")
    private Long amount;
    /**
     * 商品名称
     */
    @TableField(exist = false)
    private String name;
    /**
     * 商品图片
     */
    @TableField(exist = false)
    private String img;
    /**
     * 商品价格
     */
    @TableField(exist = false)
    private BigDecimal price;

}

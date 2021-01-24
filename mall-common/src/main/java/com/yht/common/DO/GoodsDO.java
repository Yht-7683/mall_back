package com.yht.common.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 商品实体类
 */
@Data
@TableName("goods_spu")
public class GoodsDO {
    /**
     * 货商品d
     */
    @TableId(type = IdType.AUTO)
    private Long goodsId;
    /**
     * 商品名称
     */
    @NotBlank(message = "名不能为空")
    private String name;
    /**
     * 简介
     */
    private String details;
    /**
     * 类型id
     */
    private String classificationId;
    /**
     * 类型名
     */
    @TableField(exist=false)
    private String classificationName;
    /**
     * 图片url
     */
    private String img;

}

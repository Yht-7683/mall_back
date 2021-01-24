package com.yht.common.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 商品类型表实体类
 */
@Data
@TableName("goods_spu_classification")
public class GoodsClassificationDO {
    /**
     * 货商品d
     */
    @TableId(type = IdType.AUTO)
    private Long classificationId;
    /**
     * 类型名称
     */
    @NotBlank(message = "类型名不能为空")
    private String classificationName;
    /**
     * 其他介绍
     */
    private String others;
}

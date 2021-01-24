package com.yht.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yht.common.DO.GoodsClassificationDO;


/**
 * 商品类型
 */
public interface GoodsClassificationService extends IService<GoodsClassificationDO> {

    /**
     * 按照类别id查找
     */
    GoodsClassificationDO selectByGoodsId(Long id);

}

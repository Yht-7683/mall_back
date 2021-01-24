package com.yht.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yht.common.DO.GoodsDO;
import com.yht.common.DO.GoodsStockDO;
import com.yht.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 商品库存管理
 */
public interface GoodsStockService extends IService<GoodsStockDO> {
    /**
     * 查询所有商品的库存
     */
    PageUtils queryPage(Map<String, Object> map);
    /**
     * 批量（单个）删除商品库存
     */
    void deleteByIds(List<Long> goodsId);
    /**
     * 按照库存id查找
     */
    GoodsStockDO selectByskuId(Long id);
    /**
     * 修改商品库存信息
     */
    void update(GoodsStockDO stock);

    /**
     *  添加初始商品库存信息（都为0）
     */
    void saveStock(Long goodsId);
}

package com.yht.mall.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yht.common.DO.GoodsDO;
import com.yht.common.DO.GoodsStockDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsStockDao extends BaseMapper<GoodsStockDO> {
    /**
     * 分页查找
     */
    IPage<GoodsStockDO> searchPage(IPage<GoodsStockDO> page, @Param("name") String name);

    /**
     * 多表查商品信息
     */
    GoodsStockDO selectByskuId(Long id);
    /**
     * 按照商品id批量删除
     */
    void deleteByGoodsIds(List<Long> ids);
    /**
     * 获取当前库存
     */
    Long getGoodsAmount(@Param("skuId") Long skuId);
    /**
     * 修改库存
     */
    void updateGoodsAmount(@Param("skuId") Long skuId,@Param("amount") Long amount);

}

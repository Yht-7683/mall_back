package com.yht.mall.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yht.common.DO.GoodsDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 商品信息
 */
@Mapper
public interface GoodsDao extends BaseMapper<GoodsDO> {
    /**
     * 所有商品列表
     */
    IPage<GoodsDO> searchPage(IPage<GoodsDO> page, @Param("name") String name, @Param("classificationName") String classificationName);
    /**
     * 按照不同类别列出商品信息（前台系统）
     */
    List<Map<String, Object>> listByClass(@Param("classificationName") String classificationName);

    /**
     * 获取商品详细信息（前台系统）
     * @return
     */
    Map<String, Object> detail(@Param("goodsId") Long goodsId);

}

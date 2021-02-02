package com.yht.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yht.common.DO.GoodsDO;
import com.yht.common.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 商品管理crud
 * @author yht
 */
public interface GoodsService extends IService<GoodsDO> {
    /**
     * 查询所有商品
     */
    PageUtils queryPage(Map<String, Object> map);
    /**
     * 批量（单个）删除商品
     */
    void deleteByIds(List<Long> goodsId);
    /**
     * 修改商品信息
     */
    void update(GoodsDO goods);

    /**
     * 添加商品
     */
    void saveGoods(GoodsDO goods);
    /**
     * 按照商品id查找
     */
    GoodsDO selectByGoodsId(Long id);

    /**
     * 按照不同类别列出商品信息（前台系统）
     */
    List<Map<String, Object>> listByClass(String classificationName);

    /**
     * 获取商品详细信息
     * @return
     */
    Map<String, Object> detail(Long goodsId);
}

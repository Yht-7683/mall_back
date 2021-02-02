package com.yht.mall.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yht.common.DO.CartDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 购物车
 */
@Mapper
public interface CartDao extends BaseMapper<CartDO> {
    /**
     * 当前用户的购物车
     */
    List<CartDO> listCart(@Param("userId") Long userId);
    /**
     * 修改商品在购物车中的数量
     */
    boolean updateAmount(CartDO cartDO);
}

package com.yht.mall.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yht.common.DO.OrderInfoDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 总体订单
 */
@Mapper
public interface OrderInfoDao extends BaseMapper<OrderInfoDO> {
    /**
     * 分页查找
     */
    IPage<OrderInfoDO> searchPage(IPage<OrderInfoDO> page, @Param("username") String username);

}

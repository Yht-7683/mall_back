package com.yht.mall.order.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yht.common.DO.GoodsStockDO;
import com.yht.common.DO.OrderInfoDO;
import com.yht.common.utils.PageUtils;
import com.yht.mall.order.dao.OrderInfoDao;
import com.yht.mall.order.service.OrderInfoService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoDao, OrderInfoDO> implements OrderInfoService {
    @Override
    public PageUtils queryPage(Map<String, Object> map) {
        int pageSize = Integer.parseInt(String.valueOf(map.get("limit")));
        int currPage =  Integer.parseInt(String.valueOf( map.get("page")));
        String name = (String)map.get("username");
        IPage<OrderInfoDO> page = baseMapper.searchPage(new Page<>(currPage,pageSize),name);
        return new PageUtils(page);
    }
}

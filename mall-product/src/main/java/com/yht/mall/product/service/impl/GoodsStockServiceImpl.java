package com.yht.mall.product.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yht.common.DO.GoodsStockDO;
import com.yht.common.utils.PageUtils;
import com.yht.mall.product.dao.GoodsStockDao;
import com.yht.mall.product.service.GoodsStockService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class GoodsStockServiceImpl extends ServiceImpl<GoodsStockDao, GoodsStockDO> implements GoodsStockService {
    @Override
    public PageUtils queryPage(Map<String, Object> map) {
        int pageSize = Integer.parseInt(String.valueOf(map.get("limit")));
        int currPage =  Integer.parseInt(String.valueOf( map.get("page")));
        String name = (String)map.get("name");
        IPage<GoodsStockDO> page = baseMapper.searchPage(new Page<>(currPage,pageSize),name);
        return new PageUtils(page);
    }

    @Override
    public void deleteByIds(List<Long> goodsId) {
        baseMapper.deleteByGoodsIds(goodsId);
    }

    @Override
    public GoodsStockDO selectByskuId(Long id) {
        return baseMapper.selectByskuId(id);
    }

    @Override
    public void update(GoodsStockDO stock) {
        this.updateById(stock);
    }

    @Override
    public void saveStock(Long goodsId) {
        GoodsStockDO stock = new GoodsStockDO();
        stock.setAmount(0L);
        stock.setPrice(new BigDecimal(0));
        stock.setStatus(0);
        stock.setGoodsId(goodsId);
        this.save(stock);
    }

    @Override
    public Long getGoodsAmount(Long skuId) {
        return baseMapper.getGoodsAmount(skuId);
    }

    @Override
    public void updataGoodsAmount(Long skuId,Long amount) {
        baseMapper.updateGoodsAmount(skuId,amount);
    }
}

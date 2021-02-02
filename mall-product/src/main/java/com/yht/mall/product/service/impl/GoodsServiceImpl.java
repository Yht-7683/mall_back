package com.yht.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yht.common.DO.GoodsDO;
import com.yht.common.utils.PageUtils;
import com.yht.mall.product.dao.GoodsDao;
import com.yht.mall.product.service.GoodsService;
import com.yht.mall.product.service.GoodsStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsDao, GoodsDO> implements GoodsService {
    @Autowired
    private GoodsStockService goodsStockService;
    @Override
    public PageUtils queryPage(Map<String, Object> map) {
        int pageSize = Integer.parseInt(String.valueOf(map.get("limit")));
        int currPage =  Integer.parseInt(String.valueOf( map.get("page")));
        String name = (String)map.get("name");
        String classification = (String)map.get("classificationName");
        IPage<GoodsDO> page = baseMapper.searchPage(new Page<>(currPage,pageSize),name,classification );
        return new PageUtils(page);

    }

    @Override
    public void deleteByIds(List<Long> goodsId) {
        this.removeByIds(goodsId);
        goodsStockService.deleteByIds(goodsId);
    }

    @Override
    public void update(GoodsDO goods) {
        this.updateById(goods);
    }

    @Override
    public void saveGoods(GoodsDO goods) {
        //添加商品信息
        this.save(goods);
        //添加商品对应的库存信息
        goodsStockService.saveStock(goods.getGoodsId());
    }

    @Override
    public GoodsDO selectByGoodsId(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public List<Map<String, Object>> listByClass(String classificationName) {
        return baseMapper.listByClass(classificationName);
    }

    @Override
    public Map<String, Object> detail(Long goodsId) {
        return baseMapper.detail(goodsId);
    }
}

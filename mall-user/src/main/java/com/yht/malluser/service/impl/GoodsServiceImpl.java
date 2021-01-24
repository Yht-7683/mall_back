package com.yht.malluser.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yht.common.DO.GoodsDO;
import com.yht.common.utils.PageUtils;
import com.yht.malluser.dao.GoodsDao;
import com.yht.malluser.service.GoodsService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsDao, GoodsDO> implements GoodsService {
    @Override
    public PageUtils queryPage(Map<String, Object> map) {
        int pageSize = Integer.parseInt(String.valueOf(map.get("limit")));
        int currPage =  Integer.parseInt(String.valueOf( map.get("page")));
        String name = (String)map.get("name");
        IPage<GoodsDO> page = baseMapper.selectPage(new Page<>(currPage,pageSize),
                new QueryWrapper<GoodsDO>().like(StringUtils.isNotBlank(name),"name",name));
        return new PageUtils(page);
    }

    @Override
    public void deleteByIds(List<Long> goodsId) {
        this.removeByIds(goodsId);
    }

    @Override
    public void update(GoodsDO goods) {
        this.updateById(goods);
    }

    @Override
    public void saveGoods(GoodsDO goods) {
        this.save(goods);
    }

    @Override
    public GoodsDO selectByGoodsId(Long id) {
        return baseMapper.selectById(id);
    }
}

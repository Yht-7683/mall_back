package com.yht.mall.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yht.common.DO.GoodsClassificationDO;
import com.yht.common.utils.PageUtils;
import com.yht.mall.product.dao.GoodsClassificationDao;
import com.yht.mall.product.service.GoodsClassificationService;
import org.springframework.stereotype.Service;

@Service
public class GoodsClassificationServiceImpl extends ServiceImpl<GoodsClassificationDao, GoodsClassificationDO> implements GoodsClassificationService {


    @Override
    public GoodsClassificationDO selectByGoodsId(Long id) {
        return null;
    }
}

package com.yht.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yht.common.DO.SysLogDO;
import com.yht.common.utils.PageUtils;
import com.yht.sys.dao.SysLogDao;
import com.yht.sys.service.SysLogService;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SysLogServiceimpl extends ServiceImpl<SysLogDao, SysLogDO> implements SysLogService{
    @Override
    public PageUtils queryPage(Map<String, Object> map) {
        int pageSize = Integer.parseInt(String.valueOf(map.get("limit")));
        int currPage =  Integer.parseInt(String.valueOf( map.get("page")));
        String username = (String)map.get("username");
        IPage<SysLogDO>  page = baseMapper.selectPage(new Page<>(currPage, pageSize),
                new QueryWrapper<SysLogDO>().like(StringUtils.isNotBlank(username),"username", username));
        return new PageUtils(page);
    }
}

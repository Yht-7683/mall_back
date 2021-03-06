package com.yht.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yht.common.DO.SysLogDO;
import com.yht.common.utils.PageUtils;


import java.util.Map;

public interface SysLogService extends IService<SysLogDO> {
    /**
     * 查询所有日志
     */
    PageUtils queryPage(Map<String, Object> map);
}

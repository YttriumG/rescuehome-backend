package com.rescue.modules.operate.service;

import com.baomidou.mybatisplus.service.IService;
import com.rescue.common.utils.PageUtils;
import com.rescue.modules.operate.entity.WarningrecordEntity;

import java.util.Map;

public interface WarningrecordService extends IService<WarningrecordEntity> {

    PageUtils queryPage(Map<String, Object> params);
    void dealWarning(WarningrecordEntity warningrecordEntity);
}


package com.rescue.modules.operate.service;

import com.baomidou.mybatisplus.service.IService;
import com.rescue.common.utils.PageUtils;
import com.rescue.modules.operate.entity.TransferrecordEntity;

import java.util.Map;


public interface TransferrecordService extends IService<TransferrecordEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


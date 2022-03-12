package com.rescue.modules.operate.service;

import com.baomidou.mybatisplus.service.IService;
import com.rescue.common.utils.PageUtils;
import com.rescue.modules.operate.entity.AdandonrecordEntity;

import java.util.Map;

public interface AdandonrecordService extends IService<AdandonrecordEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


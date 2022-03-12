package com.rescue.modules.operate.service;

import com.baomidou.mybatisplus.service.IService;
import com.rescue.common.utils.PageUtils;
import com.rescue.modules.operate.entity.EquipmentEntity;

import java.util.List;
import java.util.Map;

public interface EquipmentService extends IService<EquipmentEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void updateAllByid( EquipmentEntity equipmentEntity,String id);

    List<String> selectAllType();

    List<String> selectAllCommunity();

    void updateStatusByid( int equipmentStatus,String id);
}


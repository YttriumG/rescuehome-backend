package com.rescue.modules.operate.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.rescue.modules.operate.entity.EquipmentEntity;
import com.rescue.modules.operate.entity.EquipmentStatistics;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EquipmentDao extends BaseMapper<EquipmentEntity> {

    void updateAllByid(@Param("equipment") EquipmentEntity equipmentEntity, @Param("id") String id);

    List<String> selectAllType();

    List<String> selectAllCommunity();

    void updateStatusByid(@Param("equipmentStatus") int equipmentStatus,@Param("equipmentId")String equipmentId);

    List<EquipmentStatistics> statisticsbydate(@Param("startDate")String startDate,@Param("endDate")String endDate);
}

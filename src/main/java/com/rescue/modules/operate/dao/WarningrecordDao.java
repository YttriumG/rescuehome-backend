package com.rescue.modules.operate.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.rescue.modules.operate.entity.WarningrecordEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WarningrecordDao extends BaseMapper<WarningrecordEntity> {

    void dealWarning(@Param("warningrecord")WarningrecordEntity warningrecordEntity);
}

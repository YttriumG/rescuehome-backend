

package com.rescue.modules.sys.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.rescue.modules.sys.entity.SysLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统日志
 */
@Mapper
public interface SysLogDao extends BaseMapper<SysLogEntity> {
	
}

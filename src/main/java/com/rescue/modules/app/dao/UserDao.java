

package com.rescue.modules.app.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.rescue.modules.app.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

}

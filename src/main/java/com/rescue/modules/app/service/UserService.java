package com.rescue.modules.app.service;


import com.baomidou.mybatisplus.service.IService;
import com.rescue.modules.app.entity.UserEntity;
import com.rescue.modules.app.form.LoginForm;

/**
 * 用户
 */
public interface UserService extends IService<UserEntity> {

	UserEntity queryByMobile(String mobile);

	/**
	 * 用户登录
	 * @param form    登录表单
	 * @return        返回用户ID
	 */
	long login(LoginForm form);
}

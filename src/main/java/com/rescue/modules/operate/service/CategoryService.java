package com.rescue.modules.operate.service;

import com.baomidou.mybatisplus.service.IService;
import com.rescue.common.utils.PageUtils;
import com.rescue.modules.operate.entity.CategoryEntity;

import java.util.Map;

public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     *
     *
     * 通过pid修改对应子菜单的pname
     * @params：Pid ,Pname
     */
    void changPnameBypid(String Pid,String Pname);
}


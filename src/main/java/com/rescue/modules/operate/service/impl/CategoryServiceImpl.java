package com.rescue.modules.operate.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.rescue.common.utils.PageUtils;
import com.rescue.common.utils.Query;
import com.rescue.modules.operate.dao.CategoryDao;
import com.rescue.modules.operate.entity.CategoryEntity;
import com.rescue.modules.operate.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Map;



@Service("CategoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CategoryEntity> page = this.selectPage(
                new Query<CategoryEntity>(params).getPage(),
                new EntityWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void changPnameBypid(String Pid, String Pname) {
        baseMapper.changPnameBypid(Pid,Pname);
    }

}

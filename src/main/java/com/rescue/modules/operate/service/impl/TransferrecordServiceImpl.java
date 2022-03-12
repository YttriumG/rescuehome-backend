package com.rescue.modules.operate.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.rescue.common.utils.PageUtils;
import com.rescue.common.utils.Query;
import com.rescue.modules.operate.dao.TransferrecordDao;
import com.rescue.modules.operate.entity.TransferrecordEntity;
import com.rescue.modules.operate.service.TransferrecordService;
import org.springframework.stereotype.Service;

import java.util.Map;



@Service("TransferrecordService")
public class TransferrecordServiceImpl extends ServiceImpl<TransferrecordDao, TransferrecordEntity> implements TransferrecordService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<TransferrecordEntity> page = this.selectPage(
                new Query<TransferrecordEntity>(params).getPage(),
                new EntityWrapper<TransferrecordEntity>()
        );

        return new PageUtils(page);
    }

}

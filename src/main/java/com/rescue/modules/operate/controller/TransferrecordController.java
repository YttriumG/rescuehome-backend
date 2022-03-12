package com.rescue.modules.operate.controller;

import com.rescue.common.utils.PageUtils;
import com.rescue.common.utils.R;
import com.rescue.common.validator.ValidatorUtils;
import com.rescue.modules.operate.entity.TransferrecordEntity;
import com.rescue.modules.operate.service.TransferrecordService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("sys/transferrecord")
public class TransferrecordController {
    @Autowired
    private TransferrecordService transferrecordService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:rh_transferrecord:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = transferrecordService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{recordId}")
    @RequiresPermissions("sys:rh_transferrecord:info")
    public R info(@PathVariable("recordId") String recordId){
        TransferrecordEntity rh_Transferrecord = transferrecordService.selectById(recordId);

        return R.ok().put("rh_Transferrecord", rh_Transferrecord);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:rh_transferrecord:save")
    public R save(@RequestBody TransferrecordEntity rh_Transferrecord){
        transferrecordService.insert(rh_Transferrecord);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:rh_transferrecord:update")
    public R update(@RequestBody TransferrecordEntity rh_Transferrecord){
        ValidatorUtils.validateEntity(rh_Transferrecord);
        transferrecordService.updateAllColumnById(rh_Transferrecord);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:rh_transferrecord:delete")
    public R delete(@RequestBody String[] recordIds){
        transferrecordService.deleteBatchIds(Arrays.asList(recordIds));

        return R.ok();
    }

}

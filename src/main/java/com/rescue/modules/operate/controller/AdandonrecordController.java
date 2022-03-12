package com.rescue.modules.operate.controller;

import com.rescue.common.utils.PageUtils;
import com.rescue.common.utils.R;
import com.rescue.common.validator.ValidatorUtils;
import com.rescue.modules.operate.entity.AdandonrecordEntity;
import com.rescue.modules.operate.service.AdandonrecordService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("sys/adandonrecord")
public class AdandonrecordController {
    @Autowired
    private AdandonrecordService adandonrecordService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:rh_adandonrecord:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = adandonrecordService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{recordId}")
    @RequiresPermissions("sys:rh_adandonrecord:info")
    public R info(@PathVariable("recordId") String recordId){
        AdandonrecordEntity rh_Adandonrecord = adandonrecordService.selectById(recordId);

        return R.ok().put("rh_Adandonrecord", rh_Adandonrecord);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:rh_adandonrecord:save")
    public R save(@RequestBody AdandonrecordEntity rh_Adandonrecord){
        adandonrecordService.insert(rh_Adandonrecord);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:rh_adandonrecord:update")
    public R update(@RequestBody AdandonrecordEntity rh_Adandonrecord){
        ValidatorUtils.validateEntity(rh_Adandonrecord);
        adandonrecordService.updateAllColumnById(rh_Adandonrecord);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:rh_adandonrecord:delete")
    public R delete(@RequestBody String[] recordIds){
        adandonrecordService.deleteBatchIds(Arrays.asList(recordIds));

        return R.ok();
    }

}

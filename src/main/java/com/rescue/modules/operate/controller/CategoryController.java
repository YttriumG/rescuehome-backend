package com.rescue.modules.operate.controller;

import com.rescue.common.utils.DateUtils;
import com.rescue.common.utils.PageUtils;
import com.rescue.common.utils.R;
import com.rescue.common.validator.ValidatorUtils;
import com.rescue.modules.operate.entity.CategoryEntity;
import com.rescue.modules.operate.service.CategoryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("operate:category:list")
    public R list(@RequestParam Map<String, Object> params){

        //分类列表不参与分页
        params.put("page","1");
        params.put("limit","1000");
        PageUtils page = categoryService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{categoryId}")
    @RequiresPermissions("operate:category:info")
    public R info(@PathVariable("categoryId") String categoryId){
        CategoryEntity rh_Category = categoryService.selectById(categoryId);

        return R.ok().put("rh_Category", rh_Category);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("operate:category:save")
    public R save(@RequestBody CategoryEntity rh_Category){
        rh_Category.setTypeId(rh_Category.getTypePid()+DateUtils.getHHmmssTime());
        rh_Category.setGmtCreate(new Date());
        categoryService.insert(rh_Category);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("operate:category:update")
    public R update(@RequestBody CategoryEntity rh_Category){
        ValidatorUtils.validateEntity(rh_Category);
        rh_Category.setStatus(0);
        rh_Category.setGmtCreate(new Date());
        categoryService.updateAllColumnById(rh_Category);//全部更新
        // if 名称不为null，级联更新子菜单的Pname
        if (rh_Category.getTypeName() != "" && !rh_Category.getTypeName().equals("")) {
            categoryService.changPnameBypid(rh_Category.getTypeId(), rh_Category.getTypeName());
        }
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("operate:category:delete")
    public R delete(@RequestParam Map<String, Object> params){
        Long categoryId = Long.valueOf(params.get("categoryId").toString());
        categoryService.deleteById(categoryId);
        return R.ok();
    }

}

package com.rescue.modules.operate.service;

import com.baomidou.mybatisplus.service.IService;
import com.rescue.common.utils.PageUtils;
import com.rescue.modules.operate.entity.LocationEntity;

import java.util.List;
import java.util.Map;

public interface LocationService extends IService<LocationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void updateRemarkByid(String locationid, String remark);

    void updateStatusByid(String locationid, String status);

    /**
     *根据市(citycode)查询所有区
     * */
    List<String> selectDistrictBycityCode(String citycode);
    /**
     *根据市(citycode)查询所有区
     **/
    List<LocationEntity> selectBydistrict(String district);
}


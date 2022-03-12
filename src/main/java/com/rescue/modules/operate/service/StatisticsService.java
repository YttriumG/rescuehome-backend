package com.rescue.modules.operate.service;

import com.rescue.modules.operate.entity.EquipmentStatistics;
import com.rescue.modules.operate.entity.LocationStatistics;

import java.util.List;

public interface StatisticsService {
    List<EquipmentStatistics> EquipmentStatistics(String startDate, String endDate);

    /**
     *获取按区分类统计小区数量
     * */
    List<LocationStatistics> getLocationStatistics();
}

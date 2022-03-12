
package com.rescue.modules.sys.service;


import com.baomidou.mybatisplus.service.IService;
import com.rescue.common.utils.PageUtils;
import com.rescue.modules.sys.entity.SysLogEntity;

import java.util.Map;


/**
 * 系统日志
 */
public interface SysLogService extends IService<SysLogEntity> {

    PageUtils queryPage(Map<String, Object> params);

}

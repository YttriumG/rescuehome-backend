package com.rescue.modules.operate.controller;

import com.rescue.common.utils.DateUtils;
import com.rescue.common.utils.PageUtils;
import com.rescue.common.utils.PoiUtils;
import com.rescue.common.utils.R;
import com.rescue.common.validator.ValidatorUtils;
import com.rescue.modules.operate.entity.WarningrecordEntity;
import com.rescue.modules.operate.service.EquipmentService;
import com.rescue.modules.operate.service.WarningrecordService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;


@RestController
@RequestMapping("/report")
public class WarningrecordController {
    @Autowired
    private WarningrecordService warningrecordService;
    @Autowired
    private EquipmentService equipmentService;
    @Value("${spring.file.basefileroot}")
    private  String  FILEROOTBASE;


    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("operate:warningrecord:list")
    public R list(@RequestParam Map<String, Object> params){
        params.put("sidx","warning_time");
        params.put("order","DESC");
        PageUtils page = warningrecordService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/export")
    @RequiresPermissions("operate:warningrecord:list")
    public void export(@RequestParam Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) throws IOException {
        params.put("sidx","warning_time");
        params.put("order","DESC");
        PageUtils page = warningrecordService.queryPage(params);
        List<WarningrecordEntity> warningrecord = (List<WarningrecordEntity>) page.getList();
        String sheetName = "";
        String fileName = "";
        if(params.get("page").equals("1") && params.get("limit").equals("20")){
            sheetName = "报警志愿者-所有页位置数据"+DateUtils.getNowTimetohm();
        }else {
            sheetName = "报警志愿者-当前页位置数据"+DateUtils.getNowTimetohm();
        }
        String sheetTitle  = "报警志愿者数据";
        fileName = sheetName;
        List<String> columnNames = new LinkedList<>();
        columnNames.add("记录id");
        columnNames.add("志愿者");
        columnNames.add("志愿者名称");
        columnNames.add("报警人");
        columnNames.add("报警时间");
        columnNames.add("报警项");
        columnNames.add("报警原因");
        columnNames.add("处理人");
        columnNames.add("处理结果");
        columnNames.add("处理时间");
        columnNames.add("处理方案");
        //写入标题--第二种方式
        try {
            PoiUtils.writeExcelTitle(FILEROOTBASE, fileName, sheetName, columnNames, sheetTitle, false);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<List<Object>> data = new LinkedList<>();
        int size = warningrecord.size();
        for (int i = 0; i < size; i++) {
            List<Object> dataA = new LinkedList<>();
            dataA.add(warningrecord.get(i).getRecordId());
            dataA.add(warningrecord.get(i).getEquipmentId());
            dataA.add(warningrecord.get(i).getEquipmentName());
            dataA.add(warningrecord.get(i).getWarningOperator());
            dataA.add(warningrecord.get(i).getWarningTime());
            if (warningrecord.get(i).getOperatorStatus() == 1) {
                dataA.add("报修");
            }
            else {
                dataA.add("报废");
            }
            dataA.add(warningrecord.get(i).getWarningReason());
            dataA.add(warningrecord.get(i).getDealOperator());
            if (warningrecord.get(i).getStatus() == 0) {
                dataA.add("已处理");
            }
            else {
                dataA.add("未处理");
            }
            dataA.add(warningrecord.get(i).getDealTime());
            dataA.add(warningrecord.get(i).getDealPlan());
            data.add(dataA);
        }
        try {
            //写入数据--第二种方式
            File file = PoiUtils.writeExcelData(FILEROOTBASE, fileName, sheetName, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            fileName = URLDecoder.decode(fileName,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        File file=new File(FILEROOTBASE+"/"+fileName+".xls");
        if (file.exists()) {
            String filepath = FILEROOTBASE+"/"+fileName+".xls";
            // 为了使下载框中显示中文文件名称不出乱码！必须对文件名进行编码
            String filename = getFilename(request,fileName);
            String contentDisposition = "attachment;filename=" + filename;
            // 一个流
            FileInputStream input = new FileInputStream(filepath);
            //设置头
            response.setHeader("Content-Type", "application/octet-stream");
            response.setHeader("Content-Disposition", contentDisposition);
            response.setContentType("application/octet-stream");
            response.setContentLength((int) file.length());

            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                byte[] buffer = new byte[128];
                int count = 0;
                while ((count = fis.read(buffer)) > 0) {
                    response.getOutputStream().write(buffer, 0, count);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                response.getOutputStream().flush();
                response.getOutputStream().close();
                fis.close();
                file.delete();
            }
        }else {
            response.setStatus(404);
        }
    }

    // 根据不同的浏览器进行编码设置，返回编码后的文件名
    public String getFilename(HttpServletRequest request, String filename) throws UnsupportedEncodingException {
        String agent = request.getHeader("User-Agent"); //获取浏览器
        if (agent.contains("Firefox")) {
            BASE64Encoder base64Encoder = new BASE64Encoder();
            filename = "=?utf-8?B?"
                    + base64Encoder.encode(filename.getBytes("utf-8"))
                    + "?=";
        } else if(agent.contains("MSIE")) {
            filename = URLEncoder.encode(filename, "utf-8");
        } else {
            filename = URLEncoder.encode(filename, "utf-8");
        }
        return filename;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{recordId}")
    @RequiresPermissions("operate:warningrecord:info")
    public R info(@PathVariable("recordId") String recordId){
        WarningrecordEntity rh_Warningrecord = warningrecordService.selectById(recordId);

        return R.ok().put("rh_Warningrecord", rh_Warningrecord);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("operate:warningrecord:save")
    public R save(@RequestBody WarningrecordEntity rh_Warningrecord){
        rh_Warningrecord.setWarningTime(new Date());
        rh_Warningrecord.setStatus(1);
        warningrecordService.insert(rh_Warningrecord);
        equipmentService.updateStatusByid(rh_Warningrecord.getOperatorStatus(),rh_Warningrecord.getEquipmentId());
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("operate:warningrecord:update")
    public R update(@RequestBody WarningrecordEntity rh_Warningrecord){
        ValidatorUtils.validateEntity(rh_Warningrecord);
        rh_Warningrecord.setDealTime(new Date());
        warningrecordService.updateAllColumnById(rh_Warningrecord);//全部更新

        return R.ok();
    }

    /**
     * 处理报警
     */
    @RequestMapping("/dealwarning")
    @RequiresPermissions("operate:warningrecord:update")
    public R dealwarning(@RequestBody WarningrecordEntity rh_Warningrecord){
        ValidatorUtils.validateEntity(rh_Warningrecord);
        rh_Warningrecord.setDealTime(new Date());
        warningrecordService.dealWarning(rh_Warningrecord);
        return R.ok();
    }
    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("operate:warningrecord:delete")
    public R delete(@RequestBody String[] recordIds){
        warningrecordService.deleteBatchIds(Arrays.asList(recordIds));

        return R.ok();
    }

}

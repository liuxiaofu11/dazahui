package com.tengda.dazahui.system.controller;

import com.tengda.dazahui.conmon.utils.CommonPage;
import com.tengda.dazahui.conmon.utils.CommonResult;
import com.tengda.dazahui.system.domian.AlarmRecord;
import com.tengda.dazahui.system.domian.AlarmRecordCount;
import com.tengda.dazahui.system.service.EsAlarmInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author teswell
 * @Date 2020/11/18 14:50
 * @function
 */
@Controller
public class EsAlarmInfoController {
    @Autowired
    private EsAlarmInfoService esAlarmInfoService;

    /**
     * 从数据库导入es
     *
     * @return
     */
    @RequestMapping(value = "/importAll", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> importAllList() {
        int count = esAlarmInfoService.importAll();
        return CommonResult.success(count);
    }

    /**
     * 简单查询
     * @param vehicle
     * @param statTime
     * @param endTime
     * @param alarmTypeId
     * @param alarmGrade
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/search")
    @ResponseBody
    public CommonResult<CommonPage<AlarmRecord>> search(Integer vehicle, String statTime, String endTime, Integer alarmTypeId,
                                                        Integer alarmGrade, Integer pageNum, Integer pageSize) {
        Page<AlarmRecord> esProductPage = esAlarmInfoService.search(vehicle, statTime, endTime, alarmTypeId, alarmGrade, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(esProductPage));
    }

    /**
     * 聚合数据查询
     * @param vehicle
     * @param statTime
     * @param endTime
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/searchCount")
    @ResponseBody
    public CommonResult<CommonPage<AlarmRecord>> searchCount(Integer vehicle, String statTime, String endTime, Integer pageNum, Integer pageSize) {
        Page<AlarmRecord> alarmRecordCounts = esAlarmInfoService.searchCount(vehicle,statTime,endTime,pageNum,pageSize);
        return CommonResult.success(CommonPage.restPage(alarmRecordCounts));
    }
}

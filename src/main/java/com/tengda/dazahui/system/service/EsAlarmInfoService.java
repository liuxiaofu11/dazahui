package com.tengda.dazahui.system.service;

import com.tengda.dazahui.system.domian.AlarmRecord;
import com.tengda.dazahui.system.domian.AlarmRecordCount;
import org.springframework.data.domain.Page;

public interface EsAlarmInfoService {
    /**
     * 从数据库中导入所有报警信息到ES
     */
    int importAll();

    /**
     * 普通条件查询
     * @param vehicle
     * @param statTime
     * @param endTime
     * @param alarmTypeId
     * @param alarmGrade
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<AlarmRecord> search(Integer vehicle, String statTime, String endTime, Integer alarmTypeId, Integer alarmGrade, Integer pageNum, Integer pageSize);

    Page<AlarmRecord> searchCount(Integer vehicle, String statTime, String endTime, Integer pageNum, Integer pageSize);

}

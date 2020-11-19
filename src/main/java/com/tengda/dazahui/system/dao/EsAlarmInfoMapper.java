package com.tengda.dazahui.system.dao;

import com.tengda.dazahui.system.domian.AlarmRecord;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @Author teswell
 * @Date 2020/11/18 14:52
 * @function
 */
public interface EsAlarmInfoMapper  {

    List<AlarmRecord> findAlarmInfo();

}

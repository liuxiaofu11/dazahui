package com.tengda.dazahui.system.service.impl;

import com.tengda.dazahui.system.dao.EsAlarmInfoMapper;
import com.tengda.dazahui.system.domian.AlarmRecord;
import com.tengda.dazahui.system.repository.EsProductRepository;
import com.tengda.dazahui.system.service.EsAlarmInfoService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * @Author teswell
 * @Date 2020/11/18 14:51
 * @function
 */
@Service
public class EsAlarmInfoServiceImpl implements EsAlarmInfoService {
    @Autowired(required = false)
    private EsAlarmInfoMapper esAlarmInfoMapper;
    @Autowired
    private EsProductRepository esProductRepository;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public int importAll() {
        List<AlarmRecord> alarmRecordList = esAlarmInfoMapper.findAlarmInfo();
        Iterable<AlarmRecord> alarmRecords = esProductRepository.saveAll(alarmRecordList);
        Iterator<AlarmRecord> iterator = alarmRecords.iterator();
        int result = 0;
        while (iterator.hasNext()) {
            result++;
            iterator.next();
        }
        return result;
    }

    @Override
    public Page<AlarmRecord> search(Integer vehicle, String statTime, String endTime, Integer alarmTypeId, Integer alarmGrade, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //分页
        nativeSearchQueryBuilder.withPageable(pageable);
        if (alarmTypeId != -1) {
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            boolQueryBuilder.must(QueryBuilders.termQuery("ararmTypeId", alarmTypeId));
            nativeSearchQueryBuilder.withFilter(boolQueryBuilder);
        }
        NativeSearchQuery searchQuery = nativeSearchQueryBuilder.build();
        //SearchHits<AlarmRecord> query = elasticsearchTemplate.query(searchQuery, AlarmRecord.class);
        AggregatedPage<AlarmRecord> alarmRecords = elasticsearchTemplate.queryForPage(searchQuery, AlarmRecord.class);
        if (alarmRecords.getSize() <= 0) {
            return new PageImpl<>(null, pageable, 0);
        }
        List<AlarmRecord> content = alarmRecords.getContent();
        return new PageImpl<>(content, pageable, alarmRecords.getTotalElements());
    }

}

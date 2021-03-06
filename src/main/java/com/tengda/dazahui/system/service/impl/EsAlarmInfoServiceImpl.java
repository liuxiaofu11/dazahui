package com.tengda.dazahui.system.service.impl;

import com.tengda.dazahui.conmon.utils.DateUtil;
import com.tengda.dazahui.system.dao.EsAlarmInfoMapper;
import com.tengda.dazahui.system.domian.AlarmRecord;
import com.tengda.dazahui.system.domian.AlarmRecordCount;
import com.tengda.dazahui.system.repository.EsProductRepository;
import com.tengda.dazahui.system.service.EsAlarmInfoService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
    public Page<AlarmRecord> search(Integer vehicle, String statTime1, String endTime1, Integer alarmTypeId, Integer alarmGrade, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        long statTime = DateUtil.strTransTimeStamp2(statTime1);
        long endTime = DateUtil.strTransTimeStamp2(endTime1);
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //条件查询
        List<String> List = new ArrayList<>();
        List.add("1");
        List.add("2");
        List.add("5");
        List.add("7");
        if (List == null || List.size() <= 0) {
            return null;
        }
        //代表sql中in查询的方法 termsQuery  terms 代表一个字段多个值
        boolQueryBuilder.must(QueryBuilders.termsQuery("vehicleId", List));
        if (alarmTypeId != -1) {
            //must与fileter的区别在于fileter不用计算分值,fileter赛选过滤数据以后,这个过程会缓存查询的过程,然后排除不符合条件的数据
            //boolQueryBuilder.must(QueryBuilders.termQuery("ararmTypeId", alarmTypeId));
            boolQueryBuilder.filter(QueryBuilders.termQuery("ararmTypeId", alarmTypeId));

        }
        if (alarmGrade != -1) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("alarmGrade", alarmGrade));
        }
        if (vehicle != null) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("vehicleId", vehicle));
        }
        //区间查询某个条件
        if (statTime > 0 && endTime > 0) {  //区间查询某个条件
            boolQueryBuilder.filter(QueryBuilders.rangeQuery("startTime").from(statTime).to(endTime).includeLower(true).includeUpper(true));
        }
        String keyweord = null;
        if (keyweord != null) { //多个字段匹配某一个值 , 第一个text代表传入的条件,后边代表字段
            boolQueryBuilder.filter(QueryBuilders.multiMatchQuery("ararmTypeId", "ararmTypeId", "alarmGrade"));
        }
        if (keyweord != null) { // 单字段模糊查询
            boolQueryBuilder.filter(QueryBuilders.wildcardQuery("name", "*" + keyweord + "*"));
        }
        if (keyweord != null) {//多字段模糊查询  name中必须含有jack,interest中必须含有keyword,相当于and
            WildcardQueryBuilder queryBuilder = QueryBuilders.wildcardQuery("name", "*" + keyweord);
            WildcardQueryBuilder queryBuilder1 = QueryBuilders.wildcardQuery("interest", "*" + keyweord);
            boolQueryBuilder.must(queryBuilder);
            boolQueryBuilder.must(queryBuilder1);
        }
        if (keyweord != null) {//name中含有jack或者interest含有read，相当于or
            WildcardQueryBuilder queryBuilder = QueryBuilders.wildcardQuery("name", "*" + keyweord + "*");
            WildcardQueryBuilder queryBuilder1 = QueryBuilders.wildcardQuery("interest", "*" + keyweord + "*");
            boolQueryBuilder.should(queryBuilder);
            boolQueryBuilder.should(queryBuilder1);
        }

        nativeSearchQueryBuilder.withQuery(boolQueryBuilder);
        //按某个字段排序
        nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("startTime").order(SortOrder.DESC)); //按照某个字段排序
        //分页
        nativeSearchQueryBuilder.withPageable(pageable);
        NativeSearchQuery searchQuery = nativeSearchQueryBuilder.build();
        AggregatedPage<AlarmRecord> alarmRecords = elasticsearchTemplate.queryForPage(searchQuery, AlarmRecord.class);
        if (alarmRecords.getSize() <= 0) {
            return new PageImpl<>(null, pageable, 0);
        }
        List<AlarmRecord> content = alarmRecords.getContent();
        return new PageImpl<>(content, pageable, alarmRecords.getTotalElements());
    }

    @Override
    public Page<AlarmRecord> searchCount(Integer vehicle, String statTime1, String endTime1, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        long statTime = DateUtil.strTransTimeStamp2(statTime1);
        long endTime = DateUtil.strTransTimeStamp2(endTime1);
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder(); //构建查询
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //条件查询
        List<String> List = new ArrayList<>();
        List.add("1");
        List.add("2");
        List.add("5");
        List.add("7");
        if (List == null || List.size() == 0) {
            return null;
        }
        boolQueryBuilder.must(QueryBuilders.termsQuery("vehicleId", List));
        if (vehicle != null) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("vehicleId", vehicle));
        }
        if (statTime > 0 && endTime > 0) {
            boolQueryBuilder.filter(QueryBuilders.rangeQuery("startTime").from(statTime).to(endTime).includeLower(true).includeUpper(true));
        }
        nativeSearchQueryBuilder.withQuery(boolQueryBuilder);
        nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("vehicleId").field("vehicleId"));
        nativeSearchQueryBuilder.addAggregation(AggregationBuilders.count("ararmTypeId").field("ararmTypeId"));
        nativeSearchQueryBuilder.withPageable(pageable);
        NativeSearchQuery searchQuery = nativeSearchQueryBuilder.build();
        AggregatedPage<AlarmRecord> alarmRecordCounts = elasticsearchTemplate.queryForPage(searchQuery, AlarmRecord.class);
        Aggregation vehicleId = alarmRecordCounts.getAggregation("vehicleId");
        Aggregation ararmTypeId = alarmRecordCounts.getAggregation("ararmTypeId");

        if (alarmRecordCounts.getSize() <= 0) {
            return new PageImpl<>(null, pageable, 0);
        }
        List<AlarmRecord> content = alarmRecordCounts.getContent();
        return new PageImpl<>(content, pageable, alarmRecordCounts.getTotalElements());
    }

    @Override
    public List<String> ceshi() {
        BoolQueryBuilder queryBuilders = QueryBuilders.boolQuery();
        queryBuilders.filter(QueryBuilders.termQuery("ararmTypeId", 190));
        Iterable<AlarmRecord> search = esProductRepository.search(queryBuilders);
        Iterator<AlarmRecord> iterator = search.iterator();
        List<String> List = new ArrayList<>();
        while (iterator.hasNext()) {
            Integer ararmTypeId = iterator.next().getArarmTypeId();
            List.add(String.valueOf(ararmTypeId));
        }
        return List;
    }

}

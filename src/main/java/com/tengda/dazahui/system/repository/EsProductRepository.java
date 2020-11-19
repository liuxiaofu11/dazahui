package com.tengda.dazahui.system.repository;

import com.tengda.dazahui.system.domian.AlarmRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 搜索商品ES操作类
 * Created by macro on 2018/6/19.
 */
public interface EsProductRepository extends ElasticsearchRepository<AlarmRecord,Long> {
    /**
     * 搜索查询
     *
     * @param name              商品名称
     * @param subTitle          商品标题
     * @param keywords          商品关键字
     * @param page              分页信息
     */
   // Page<EsProduct> findByNameOrSubTitleOrKeywords(String name, String subTitle, String keywords, Pageable page);

}

package com.tengda.dazahui.system.controller;

import com.tengda.dazahui.conmon.utils.ReportStatisUtil;
import com.tengda.dazahui.system.domian.ElementReportVo;
import com.tengda.dazahui.system.service.MeiTianShuJuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * @Author teswell
 * @Date 2020/11/16 15:00
 * @function
 */
@Controller
public class MeiTianShuJuController {
    @Autowired
    private MeiTianShuJuService meiTianShuJuService;

    /**
     * 获取自动站站点汇总数据
     *
     * @param start
     * @param end
     */
    @RequestMapping("/duoxiancheng")
    public  Map<Integer, ElementReportVo> getZidongzhanSum(String start, String end) throws Exception {
        //获取时间列表
        List<String> betweenDates = ReportStatisUtil.getDays(start, end);

        //定义固定长度的线程池  防止线程过多
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        List<ElementReportVo> result = new ArrayList<>();//返回结果

        List<Callable<List<ElementReportVo>>> tasks = new ArrayList<>();
        for (String str : betweenDates) {
            Callable<List<ElementReportVo>> blockZidongzhan = getBlockZidongzhan(start, end, str); //循环数据
            tasks.add(blockZidongzhan);
        }
        //Future用于获取结果
        List<Future<List<ElementReportVo>>> futures = executorService.invokeAll(tasks);
        //处理线程返回结果
        if (futures != null && futures.size() > 0) {
            for (Future<List<ElementReportVo>> future : futures) {
                result.addAll(future.get());
            }
        }

        executorService.shutdown();//关闭线程池

        Map<Integer, ElementReportVo> zidongzhanMap = getZidongzhanMap(result);
        return zidongzhanMap;
    }

    /**
     * 查询自动站某一天的数据
     *
     * @param startTime
     * @param endTime
     * @param betweenDates
     * @return
     */
    private Callable<List<ElementReportVo>> getBlockZidongzhan(String startTime, String endTime, String betweenDates) {
        Callable<List<ElementReportVo>> callable = new Callable<List<ElementReportVo>>() {
            @Override
            public List<ElementReportVo> call() throws Exception {
                HashMap<String, Object> maps = new HashMap<>();
                maps.put("startTime", startTime);
                maps.put("endTime", endTime);
                maps.put("betweenDates", betweenDates);
                List<ElementReportVo> list = meiTianShuJuService.getZidongzhanLists(maps);
                return list;
            }
        };
        return callable;
    }

    /**
     * 获取自动站maps
     *
     * @param list
     * @return
     */
    public Map<Integer, ElementReportVo> getZidongzhanMap(List<ElementReportVo> list) {
        Map<Integer, List<ElementReportVo>> collect = list.stream().collect(Collectors.groupingBy(ElementReportVo::getStationInfoId));
        Map<Integer, ElementReportVo> maps = new HashMap<Integer, ElementReportVo>();
        for (Map.Entry<Integer, List<ElementReportVo>> elEntry : collect.entrySet()) {
            //一个月的数据
            List<ElementReportVo> elementReportVos = elEntry.getValue();
            int spd = elementReportVos.stream().mapToInt(ElementReportVo::getSpd).sum();
            int dir = elementReportVos.stream().mapToInt(ElementReportVo::getDir).sum();
            int hum = elementReportVos.stream().mapToInt(ElementReportVo::getHum).sum();
            int pa = elementReportVos.stream().mapToInt(ElementReportVo::getPa).sum();
            int rain = elementReportVos.stream().mapToInt(ElementReportVo::getRain).sum();
            int temp = elementReportVos.stream().mapToInt(ElementReportVo::getTemp).sum();

            ElementReportVo elementReportVo = maps.get(elEntry.getKey());
            if (elementReportVo == null) {
                elementReportVo = new ElementReportVo();
            }
            elementReportVo.setStationInfoId(elEntry.getKey());
            elementReportVo.setDir(dir);
            elementReportVo.setHum(hum);
            elementReportVo.setPa(pa);
            elementReportVo.setRain(rain);
            elementReportVo.setSpd(spd);
            elementReportVo.setTemp(temp);
            maps.put(elEntry.getKey(), elementReportVo);
        }
        return maps;
    }

//    public static void main(String[] args) {
//        String data1 = "2020-11-10 00:00:00";
//        String data2 = "2020-11-11 00:00:00";
//        Map<Integer, ElementReportVo> zidongzhanSum = getZidongzhanSum(data1, data2);
//
//    }
}

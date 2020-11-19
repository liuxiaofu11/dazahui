package com.tengda.dazahui.system.service;

import com.tengda.dazahui.system.domian.ElementReportVo;

import java.util.HashMap;
import java.util.List;

public interface MeiTianShuJuService {

    List<ElementReportVo> getZidongzhanLists(HashMap<String, Object> maps);

}

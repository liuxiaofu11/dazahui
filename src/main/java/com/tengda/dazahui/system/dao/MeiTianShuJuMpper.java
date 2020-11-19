package com.tengda.dazahui.system.dao;

import com.tengda.dazahui.system.domian.ElementReportVo;

import java.util.HashMap;
import java.util.List;

public interface MeiTianShuJuMpper {
    List<ElementReportVo> getZidongzhanLists(HashMap<String, Object> maps);

}

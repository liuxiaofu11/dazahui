package com.tengda.dazahui.system.service.impl;

import com.tengda.dazahui.system.dao.MeiTianShuJuMpper;
import com.tengda.dazahui.system.domian.ElementReportVo;
import com.tengda.dazahui.system.service.MeiTianShuJuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author teswell
 * @Date 2020/11/16 15:18
 * @function
 */
@Service
public class MeiTianShuJuServiceImpl  implements MeiTianShuJuService {
    @Autowired(required = false)
    MeiTianShuJuMpper  meiTianShuJuMpper ;
    @Override
    public List<ElementReportVo> getZidongzhanLists(HashMap<String, Object> maps) {
        List<ElementReportVo> elementReportVos=  meiTianShuJuMpper.getZidongzhanLists(maps);
        return elementReportVos;
    }
}

package com.tengda.dazahui.conmon.poi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * @Author teswell
 * @Date 2020/12/14 11:34
 * @function 读取路径下的模板填充数据
 */
public class ExcelDuQu {
    public  void method(HttpServletRequest request, HttpServletResponse response) {
        String temlateRealPath = request.getSession().getServletContext().getRealPath("static") +
                File.separator + "muban" + File.separator + "运行数据.xls";
        System.out.println(temlateRealPath);
    }

}

package com.tengda.dazahui.system.controller;

import com.tengda.dazahui.system.domian.User;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author teswell
 * @Date 2020/12/14 11:34
 * @function 读取路径下的模板填充数据
 */
@Controller
public class ExcelDuQu {

    @RequestMapping("/export")
    @ResponseBody
    public void method(HttpServletRequest request, HttpServletResponse response) {
        List<User> List = new ArrayList<>();
        User user1 = new User();
        user1.setUserName("王五");
        user1.setAge(20);
        List.add(user1);

        User user2 = new User();
        user2.setUserName("张三");
        user2.setAge(10);
        List.add(user2);

        User user3 = new User();
        user3.setUserName("马六");
        user3.setAge(30);
        List.add(user3);

        //读取模板
//        String temlateRealPath = request.getSession().getServletContext().getRealPath("static") +
//                File.separator + "muban" + File.separator + "运行数据.xls";

        String temlateRealPath = request.getSession().getServletContext().getRealPath("template") +
                File.separator + "运行数据.xls";
        //读取模板创建的excel表格对象
        XSSFWorkbook workbook;
        try {
            workbook = new XSSFWorkbook(new FileInputStream(new File(temlateRealPath)));
            XSSFSheet sheet = workbook.getSheetAt(0);
            XSSFRow row = sheet.getRow(3);
            row.getCell(1).setCellValue(50);//车辆总数
            int rowNum = 3;
            for (User user : List) {
                row = sheet.getRow(rowNum++);
                row.getCell(1).setCellValue(user.getUserName());
                row.getCell(2).setCellValue(user.getAge());
            }
            ServletOutputStream out = response.getOutputStream();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("content-Disposition", "attachment;filename=report.xlsx");
            workbook.write(out);
            out.flush();
            out.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}

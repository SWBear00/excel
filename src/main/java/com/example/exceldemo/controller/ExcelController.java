package com.example.exceldemo.controller;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.example.exceldemo.listener.ExcelListener;
import com.example.exceldemo.service.ExcelService;
import com.example.exceldemo.vo.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    ExcelService excelService;


    /**
     * 导出excel
     * 1.添加响应头信息；
     *
     * 2.添加 ExcelWriter；
     *
     * 3.添加 Sheet（表单）；
     *
     * 4.添加数据；
     *
     * 5.输出
     * @return
     */
    @RequestMapping("/export")
    public String exportExcel(HttpServletResponse response){


        ExcelWriter writer = null;
        OutputStream outputStream=null;
        try {
          outputStream = response.getOutputStream();

          //添加响应头信息
        response.setHeader("Content-disposition", "attachment; filename=" + "catagory.xls");
        response.setContentType("application/msexcel;charset=UTF-8");//设置类型
        response.setHeader("Pragma", "No-cache");//设置头
        response.setHeader("Cache-Control", "no-cache");//设置头
        response.setDateHeader("Expires", 0);//设置日期头

            //实例换excelwrite
            writer = new ExcelWriter(outputStream, ExcelTypeEnum.XLS,true);

            //实例化表单
            Sheet sheet = new Sheet(1, 0, Login.class);
            sheet.setSheetName("登录人员信息");

            //获取数据
            List<Login> catagoryList = excelService.findAll();
            System.out.println(catagoryList.toString());

            writer.write(catagoryList,sheet);
            writer.finish();
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    return  "index";
    }

    /**
     * 导入excel
     *
     1.实例化 ExcelListener；

     2.实例化 ExcelReader；

     3.读取表格信息；

     4.向数据库插入数据。
     */
    @RequestMapping("/import")
    @ResponseBody
    public List<Object> importExcel(@RequestParam("file") MultipartFile file, Model model){

        //定义输入流
        InputStream inputStream = null;
        List<Object> list=null;
        try {
           inputStream= file.getInputStream();


            //实例化实现了AnalysisEventListener接口的类
            ExcelListener listener = new ExcelListener();
            System.out.println("监听创建完成");

            //读取excel
            ExcelReader reader = new ExcelReader(inputStream,ExcelTypeEnum.XLS,null,listener);

            //读取信息
            reader.read(new Sheet(1, 1, Login.class));

            //获取数据
           list = listener.getDatas();

            List<Login> catagoryList = new ArrayList<Login>();
            Login catagory = new Login();

            //转换数据类型,并插入到数据库
            for (int i = 0; i < list.size(); i++) {
                catagory = (Login) list.get(i);
                System.out.println(i+":"+catagory.toString());
                excelService.insertLogin(catagory);
                model.addAttribute(""+i,catagory);


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

}

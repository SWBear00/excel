package com.example.exceldemo.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.exceldemo.vo.Login;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用 EasyExcel，我们需要继承 AnalysisEventListener
 */
public class ExcelListener extends AnalysisEventListener<Login> {

    //可以通过实例获取该值
    private List<Object> datas = new ArrayList<Object>();
    @Override
    public void invoke(Login data, AnalysisContext context) {
        datas.add(data);//数据存储到list，供批量处理，或后续自己业务逻辑处理。
        doSomething(data);//根据自己业务做处理
    }

    private void doSomething(Object object) {
        //1、入库调用接口

    }

    public List<Object> getDatas() {
        return datas;
    }

    public void setDatas(List<Object> datas) {
        this.datas = datas;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // datas.clear();//解析结束销毁不用的资源
    }
}

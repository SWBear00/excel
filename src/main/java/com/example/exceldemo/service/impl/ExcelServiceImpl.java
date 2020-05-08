package com.example.exceldemo.service.impl;

import com.example.exceldemo.mapper.ExcelMapper;
import com.example.exceldemo.service.ExcelService;
import com.example.exceldemo.vo.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    ExcelMapper excelMapper;

    /**
     * 查询login表中的数据
     * @return
     */
    @Override
    public List<Login> findAll() {
        return excelMapper.findAll();
    }

    /**
     * 向login表中插入数据
     * @param catagory
     */
    @Override
    public void insertLogin(Login catagory) {
        excelMapper.insertLogin(catagory);
    }
}

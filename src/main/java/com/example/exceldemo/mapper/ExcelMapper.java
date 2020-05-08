package com.example.exceldemo.mapper;

import com.example.exceldemo.vo.Login;

import java.util.List;

public interface ExcelMapper {


    List<Login> findAll();

    void insertLogin(Login catagory);
}

package com.example.exceldemo.service;

import com.example.exceldemo.vo.Login;

import java.util.List;

public interface ExcelService {
    List<Login> findAll();

    void insertLogin(Login catagory);
}

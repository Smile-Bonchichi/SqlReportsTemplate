package com.company.excelReport.controller;

import com.company.excelReport.dto.RequestDto;
import com.company.excelReport.service.SqlReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class MainController {
    final SqlReportService sqlReportService;

    @Autowired
    public MainController(SqlReportService service) {
        this.sqlReportService = service;
    }

    @PostMapping("sql-report")
    public String createSqlReport(@RequestBody RequestDto requestDto) {
        return sqlReportService.getSqlReport(requestDto);
    }
}

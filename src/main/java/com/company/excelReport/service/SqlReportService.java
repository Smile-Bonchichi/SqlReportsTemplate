package com.company.excelReport.service;

import com.company.excelReport.dto.RequestDto;

public interface SqlReportService {
    String getSqlReport(RequestDto requestDto);
}

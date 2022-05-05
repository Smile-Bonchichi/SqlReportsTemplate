package com.company.excelReport.dao;

import com.company.excelReport.model.SqlDataModel;

public interface SqlReportDao extends BaseDao {
    SqlDataModel getSqlData(String sqlQuery);
}

package com.company.excelReport.dao.impl;

import com.company.excelReport.dao.SqlReportDao;

import com.company.excelReport.exception.DataBaseException;
import com.company.excelReport.exception.OutColumnsException;
import com.company.excelReport.model.SqlDataModel;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SqlReportDaoImpl implements SqlReportDao {
    final Connection connection;

    @Autowired
    public SqlReportDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public SqlDataModel getSqlData(String sqlQuery) {
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(sqlQuery);
            return SqlDataModel.builder()
                    .resultSet(resultSet)
                    .columnsCount(columnsCount(resultSet))
                    .build();
        } catch (DataBaseException | SQLException e) {
            throw new DataBaseException(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    private Long columnsCount(ResultSet resultSet) {
        int tempCounter = 0;
        try {
            resultSet.next();
            do {
                resultSet.getObject(++tempCounter);

            } while (tempCounter != 100);
        } catch (OutColumnsException | SQLException e) {
            return (long) tempCounter - 1;
        }
        return -1L;
    }
}

package com.company.excelReport.service.impl;

import com.company.excelReport.dao.SqlReportDao;
import com.company.excelReport.dto.RequestDto;
import com.company.excelReport.exception.DataBaseException;
import com.company.excelReport.exception.FileCreateException;
import com.company.excelReport.model.SqlDataModel;
import com.company.excelReport.service.SqlReportService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SqlReportServiceImpl implements SqlReportService {
    final SqlReportDao sqlReportDao;

    @Autowired
    public SqlReportServiceImpl(SqlReportDao sqlReportDao) {
        this.sqlReportDao = sqlReportDao;
    }

    @Override
    public String getSqlReport(RequestDto requestDto) {
        SqlDataModel sqlDataModel = sqlReportDao.getSqlData(requestDto.getSql());

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        int rowNumber = 0;

        Row row = sheet.createRow(rowNumber);
        createHeaderCell(row, sqlDataModel.getColumnsCount());

        try {
            while (sqlDataModel.getResultSet().next()) {
                row = sheet.createRow(++rowNumber);
                for (int i = 0; i < sqlDataModel.getColumnsCount(); i++)
                    insertExcelFile(row, i, sqlDataModel.getResultSet().getObject(i + 1));
            }
            try (FileOutputStream out = new FileOutputStream(requestDto.getUrl() + "\\Report.xls")) {
                workbook.write(out);
            } catch (FileCreateException | IOException e) {
                throw new FileCreateException(e.getMessage(), HttpStatus.CONFLICT);
            }
        } catch (DataBaseException | SQLException e) {
            throw new DataBaseException(e.getMessage(), HttpStatus.CONFLICT);
        } finally {
            try {
                sqlDataModel.getResultSet().close();
            } catch (SQLException ignored) {
            }
        }
        return "Файл успешно создан";
    }

    private void createHeaderCell(Row row, Long columnsCount) {
        for (int i = 0; i < columnsCount; i++) {
            row.createCell(i).setCellValue("№ " + (i + 1));
        }
    }

    private void insertExcelFile(Row row, int column, Object value) {
        row.createCell(column).setCellValue(value.toString());
    }
}

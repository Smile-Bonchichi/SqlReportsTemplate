package com.company.excelReport.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.ResultSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SqlDataModel {
    ResultSet resultSet;

    Long columnsCount;
}

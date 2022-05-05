package com.company.excelReport.config;

import com.company.excelReport.exception.DataBaseDriverException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class PostgresConfig {
    @Bean
    public Connection getConnect() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (DataBaseDriverException | ClassNotFoundException e) {
            throw new DataBaseDriverException("Драйвер умер", HttpStatus.CONFLICT);
        }
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/",
                "postgres",
                "postgres"
        );
    }
}

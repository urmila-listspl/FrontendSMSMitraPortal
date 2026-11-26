package com.list20.SMSMitraPortal.config;

import com.mgn.listenryption.common.CallData;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {
    @Value("${spring.datasource.username}")
    private String userName;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.url}")
    private String dbUrl;

    public DataSourceConfig() {
    }

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create().url(this.dbUrl)
                .username(CallData.getData(this.userName, "D", 1))
                .password(CallData.getData(this.password, "D", 1))
                .driverClassName("oracle.jdbc.driver.OracleDriver").build();
    }
}


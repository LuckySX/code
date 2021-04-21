package com.flow.traffic.config.dao;


import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.PropertyVetoException;

/**

 * 数据库配置类

 */

@Configuration
public class DataSourceConfiguration {

        @Value("${jdbc.driver}")

        private String jdbcDriver;

        @Value("${jdbc.url}")

        private String jdbcUrl;

        @Value("${jdbc.username}")

        private String jdbcUsername;

        @Value("${jdbc.password}")

        private String jdbcPassword;

        @Value("${jdbc.maxActive}")

        private int maxActive;

        @Value("${jdbc.maxIdle}")

        private int maxIdle;

        @Value("${jdbc.maxWait}")

        private long maxWait;



        @Bean(name = "dataSouce")

        public BasicDataSource createDataSouce() throws PropertyVetoException {

            BasicDataSource dataSource = new BasicDataSource();

            dataSource.setDriverClassName(jdbcDriver);

            dataSource.setUrl(jdbcUrl);

            dataSource.setUsername(jdbcUsername);

            dataSource.setPassword(jdbcPassword);

            dataSource.setMaxActive(maxActive);

            dataSource.setMaxWait(maxWait);

            dataSource.setMaxIdle(maxIdle);

            dataSource.setValidationQuery("SELECT 1");

            dataSource.setTestOnBorrow(true);

            dataSource.setDefaultAutoCommit(true);


            return dataSource;

        }

}

package com.zenika.demo.jooq.config;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Configuration JDBC
 */
@Configuration @EnableTransactionManagement
public class DataSourceConfiguration {
    @Bean
    public DataSource dataSource() {
        MysqlDataSource dataSource=new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setUser("sakila");
        dataSource.setPassword("sakila");
        dataSource.setDatabaseName("sakila");
        return dataSource;
    }
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}

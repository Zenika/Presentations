package com.zenika.demo.jooq.config;

import javax.sql.DataSource;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

/**
 *
 */
@Configuration
public class JooqConfiguration {
    @Bean
    DSLContext dslContext(DataSource dataSource) {
        TransactionAwareDataSourceProxy transactionAwareDataSourceProxy=new TransactionAwareDataSourceProxy(dataSource);
        transactionAwareDataSourceProxy.afterPropertiesSet();

        Settings settings=new Settings();
        settings.setRenderSchema(false);
        settings.setExecuteLogging(true);
        
        return DSL.using(transactionAwareDataSourceProxy, SQLDialect.MYSQL, settings);
    }
}

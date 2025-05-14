package com.mg.js_user_service.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class LiquibaseConfig {

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource, MyConfig properties) {
        System.out.println("---------------------------------------------------");
        System.out.println(properties.getBlackListEndPoints());
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("db/changelog.xml");
        liquibase.setContexts("default_config");
        liquibase.setDropFirst(false);
        liquibase.setDataSource(dataSource);
        return liquibase;
    }
}

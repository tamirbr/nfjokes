package com.nfjokes.config;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:app.properties")
public class DataConfig {
    @Autowired
    private Environment env;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        Resource config = new ClassPathResource("hibernate.cfg.xml");
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setConfigLocation(config);
        sessionFactory.setPackagesToScan(env.getProperty("entity.package"));
        sessionFactory.setDataSource(dataSource());
        return sessionFactory;
    }

    public static final String SECURITY_DATA_SOURCE = "SECURITY_DATAS_OURCE";

	@Bean(SECURITY_DATA_SOURCE)
    public DataSource dataSource() {
        BasicDataSource ds = new BasicDataSource();
        // UTF-8 encoding
        ds.setConnectionProperties("useUnicode=true;characterEncoding=utf8;characterSetResults=UTF-8;");
        // Driver class name
        ds.setDriverClassName(env.getProperty("db.driver"));
        // Set URL
        ds.setUrl(env.getProperty("db.url"));
        // Set username & password
        ds.setUsername(env.getProperty("db.username"));
        ds.setPassword(env.getProperty("db.password"));
        return ds;
    }

}

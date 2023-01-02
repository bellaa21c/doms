package com.skbroadband.doms.global.config;

import com.zaxxer.hikari.HikariDataSource;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * @author : 안진갑
 * @Project : SKB_WEB
 * @Package : com.skbroadband.doms.global.config
 * @File : H2ServerConfig
 * @Program :
 * @Date : 2022-12-12
 * @Comment :
 */
@Profile("sample")
@Configuration
public class H2ServerConfig {
    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server H2DatabaseServer() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9091");
    }

    @Profile("sample")
    @Configuration
    @EnableJpaRepositories(basePackages = {"com.skbroadband.doms.web.repository", "com.skbroadband.doms.sample.repository"},
            entityManagerFactoryRef = "entityManagerFactory",
            transactionManagerRef = "transactionManager")
    public static class DataSourceConfig {
        @Value("${spring.jpa.hibernate.ddl-auto}")
        private String ddlAuto;

        @Value("${spring.jpa.database-platform}")
        private String dialect;

        @Bean
        public DataSourceProperties dataSourceProperties() {
            return new DataSourceProperties();
        }

        @Bean
        public DataSource dataSource() {
            return dataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
        }

        @Bean(name = "entityManagerFactory")
        public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
            LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
            em.setDataSource(dataSource());

            // Entity Package 경로
            em.setPackagesToScan("com.skbroadband.doms.web.entity", "com.skbroadband.doms.sample.entity");
            em.setPersistenceUnitName("doms_persistence");

            HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
            em.setJpaVendorAdapter(vendorAdapter);

            // Hibernate 설정
            HashMap<String, Object> properties = new HashMap<>();
            properties.put("hibernate.hbm2ddl.auto", ddlAuto);
            properties.put("hibernate.dialect", dialect);

            em.setJpaPropertyMap(properties);

            return em;
        }

//    @Bean
//    public SqlSessionFactory sqlSessionFactory() throws Exception {
//        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
//        sqlSessionFactory.setDataSource(dataSource());
//            Objects.requireNonNull(sqlSessionFactory.getObject()).getConfiguration()
//                    .setMapUnderscoreToCamelCase(true);
//
//        return sqlSessionFactory.getObject();
//    }
//
//    @Bean
//    public SqlSessionTemplate sqlSessionTemplate(
//            SqlSessionFactory sqlSessionFactory) throws Exception {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }

        @Bean(name = "transactionManager")
        public PlatformTransactionManager transactionManager() {
            JpaTransactionManager transactionManager = new JpaTransactionManager();
            transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

            return transactionManager;
        }
    }
}

package com.hiro.cathay.test.config;

import com.hiro.cathay.test.dto.DataSourceProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@EnableJpaRepositories(
        basePackages = "com.hiro.cathay.test"
)
public class DataSourceConfig {

    @Bean
    public DataSource routingDataSource(MultiDataSourceProperties props) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        for (Map.Entry<String, DataSourceProperty> entry : props.getProperties().entrySet()) {
            log.info("create data source: {} - {}", entry.getKey(), entry.getValue());

            DataSource ds = DataSourceBuilder.create()
                    .url(entry.getValue().getUrl())
                    .driverClassName(entry.getValue().getDriverClassName())
                    .username(entry.getValue().getUsername())
                    .password(entry.getValue().getPassword())
                    .build();
            targetDataSources.put(DatabaseSource.fromName(entry.getKey()), ds);
        }

        DynamicRoutingDataSource routingDataSource = new DynamicRoutingDataSource();
        routingDataSource.setTargetDataSources(targetDataSources);
        routingDataSource.setDefaultTargetDataSource(targetDataSources.get(DatabaseSource.DB1));
        return routingDataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            DataSource routingDataSource, EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(routingDataSource)
                .packages("com.hiro.cathay.test")
                .persistenceUnit("dynamic")
                .build();
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

}

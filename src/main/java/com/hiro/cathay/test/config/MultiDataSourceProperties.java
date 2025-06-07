package com.hiro.cathay.test.config;

import com.hiro.cathay.test.dto.DataSourceProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Data
@Configuration
@ConfigurationProperties("multi.datasource")
public class MultiDataSourceProperties {

    private Map<String, DataSourceProperty> properties = new HashMap<>();

}

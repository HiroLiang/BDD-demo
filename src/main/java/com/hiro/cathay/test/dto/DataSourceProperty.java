package com.hiro.cathay.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataSourceProperty {

    private String url;

    private String driverClassName;

    private String username;

    private String password;

}

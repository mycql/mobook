package com.mycql.mobook.api.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class MobilePhoneModel {
    private String brand;
    private String device;
    private String version;
}

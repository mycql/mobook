package com.mycql.mobook.api.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycql.mobook.entity.MobilePhoneStatus;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class MobilePhoneSearchRequest {
    private String brand;
    private String device;
    private String version;
    private MobilePhoneStatus status;
}

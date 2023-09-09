package com.mycql.mobook.api.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class MobilePhoneCheckoutRequest {
    private Long clientId;
    private String brand;
    private String device;
    private String version;
}

package com.mycql.mobook.api.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class MobilePhoneCheckoutResponse {
    private LocalDateTime checkoutTime;
    private Long checkoutReferenceId;
    private MobilePhone mobilePhone;
}

package com.mycql.mobook.api.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class MobilePhoneCheckinRequest {
    private Long clientId;
    private Long phoneId;
}

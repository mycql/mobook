package com.mycql.mobook.api.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycql.mobook.entity.MobilePhoneStatus;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class MobilePhone {
    private Long id;
    private MobilePhoneStatus status;
    private MobilePhoneModel model;
}

package com.mycql.mobook.api.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class MobilePhoneCheckinResponse {
    private LocalDateTime checkinTime;
    private Long checkinReferenceId;
}

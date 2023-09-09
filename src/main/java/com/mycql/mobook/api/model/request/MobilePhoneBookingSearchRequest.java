package com.mycql.mobook.api.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycql.mobook.api.model.response.MobilePhone;
import com.mycql.mobook.entity.MobilePhoneBookingAction;
import com.mycql.mobook.entity.MobilePhoneBookingStatus;
import com.mycql.mobook.entity.MobilePhoneStatus;
import lombok.Data;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class MobilePhoneBookingSearchRequest {
    private MobilePhoneBookingAction action;
    private MobilePhoneBookingStatus bookingStatus;
    private MobilePhoneStatus phoneStatus;
    private String brand;
    private String device;
    private String version;
    private Long phoneId;
    private Long clientId;
    private LocalDate from;
    private LocalDate to;
}

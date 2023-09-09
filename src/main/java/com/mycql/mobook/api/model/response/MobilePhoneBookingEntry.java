package com.mycql.mobook.api.model.response;

import com.mycql.mobook.entity.MobilePhoneBookingAction;
import com.mycql.mobook.entity.MobilePhoneBookingStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MobilePhoneBookingEntry {
    private Long id;
    private LocalDateTime timestamp;
    private MobilePhoneBookingAction action;
    private MobilePhoneBookingStatus status;
    private MobilePhone phone;
    private MobilePhoneBookingCustomer client;
}

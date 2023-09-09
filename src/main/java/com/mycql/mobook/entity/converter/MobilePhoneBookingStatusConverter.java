package com.mycql.mobook.entity.converter;

import com.mycql.mobook.entity.MobilePhoneBookingStatus;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class MobilePhoneBookingStatusConverter extends NumberCodedConverter<MobilePhoneBookingStatus> {

    public MobilePhoneBookingStatusConverter() {
        super(MobilePhoneBookingStatus.ACCEPTED);
    }
}

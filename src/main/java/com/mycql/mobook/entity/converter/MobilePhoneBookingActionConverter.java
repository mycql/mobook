package com.mycql.mobook.entity.converter;

import com.mycql.mobook.entity.MobilePhoneBookingAction;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class MobilePhoneBookingActionConverter extends NumberCodedConverter<MobilePhoneBookingAction> {

    public MobilePhoneBookingActionConverter() {
        super(MobilePhoneBookingAction.CHECK_IN);
    }
}

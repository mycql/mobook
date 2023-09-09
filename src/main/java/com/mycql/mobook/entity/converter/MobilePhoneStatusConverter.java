package com.mycql.mobook.entity.converter;

import com.mycql.mobook.entity.MobilePhoneStatus;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class MobilePhoneStatusConverter extends NumberCodedConverter<MobilePhoneStatus> {

    public MobilePhoneStatusConverter() {
        super(MobilePhoneStatus.CHECKED_IN);
    }
}

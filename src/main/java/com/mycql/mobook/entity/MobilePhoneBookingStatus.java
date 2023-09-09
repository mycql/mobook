package com.mycql.mobook.entity;

import lombok.Getter;

@Getter
public enum MobilePhoneBookingStatus implements NumberCoded {
    ACCEPTED(0),
    REJECTED(1)
    ;

    private int code;

    MobilePhoneBookingStatus(int code) {
        setCode(code);
    }

    @Override
    public void setCode(int code) {
        this.code = code;
    }

    @SuppressWarnings("unchecked")
    @Override
    public MobilePhoneBookingStatus[] getValues() {
        return values();
    }
}

package com.mycql.mobook.entity;

import lombok.Getter;

@Getter
public enum MobilePhoneBookingAction implements NumberCoded {
    CHECK_IN(0),
    CHECK_OUT(1)
    ;

    private int code;

    MobilePhoneBookingAction(int code) {
        setCode(code);
    }

    @Override
    public void setCode(int code) {
        this.code = code;
    }

    @SuppressWarnings("unchecked")
    @Override
    public MobilePhoneBookingAction[] getValues() {
        return values();
    }
}

package com.mycql.mobook.entity;

import lombok.Getter;

@Getter
public enum MobilePhoneStatus implements NumberCoded {

    CHECKED_IN(0),
    CHECKED_OUT(1)
    ;

    private int code;

    MobilePhoneStatus(int code) {
        setCode(code);
    }

    @Override
    public void setCode(int code) {
        this.code = code;
    }

    @SuppressWarnings("unchecked")
    @Override
    public MobilePhoneStatus[] getValues() {
        return values();
    }
}

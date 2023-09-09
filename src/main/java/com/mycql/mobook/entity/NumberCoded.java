package com.mycql.mobook.entity;

public interface NumberCoded {
    int getCode();

    void setCode(int code);

    <T extends NumberCoded> T[] getValues();
}

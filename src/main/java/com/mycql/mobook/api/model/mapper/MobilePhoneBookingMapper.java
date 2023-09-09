package com.mycql.mobook.api.model.mapper;

import com.mycql.mobook.api.model.response.MobilePhone;
import com.mycql.mobook.api.model.response.MobilePhoneBookingCustomer;
import com.mycql.mobook.api.model.response.MobilePhoneBookingEntry;
import com.mycql.mobook.api.model.response.MobilePhoneModel;
import com.mycql.mobook.entity.MobilePhoneBooking;
import com.mycql.mobook.entity.MobilePhoneBookingClient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MobilePhoneBookingMapper {

    MobilePhoneBookingEntry entityToDto(MobilePhoneBooking entity);
    MobilePhoneBooking dtoToEntity(MobilePhoneBookingEntry dto);

    MobilePhoneBookingCustomer entityToDto(MobilePhoneBookingClient entity);
    MobilePhoneBookingClient dtoToEntity(MobilePhoneBookingCustomer dto);
    MobilePhone entityToDto(com.mycql.mobook.entity.MobilePhone source);
    com.mycql.mobook.entity.MobilePhone dtoToEntity(MobilePhone destination);

    MobilePhoneModel entityToDto(com.mycql.mobook.entity.MobilePhoneModel source);
    com.mycql.mobook.entity.MobilePhoneModel dtoToEntity(MobilePhoneModel destination);
}

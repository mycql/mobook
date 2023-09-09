package com.mycql.mobook.api.model.mapper;

import com.mycql.mobook.api.model.response.MobilePhoneBookingCustomer;
import com.mycql.mobook.entity.MobilePhoneBookingClient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MobilePhoneBookingClientMapper {
    MobilePhoneBookingCustomer entityToDto(MobilePhoneBookingClient entity);
    MobilePhoneBookingClient dtoToEntity(MobilePhoneBookingCustomer dto);
}

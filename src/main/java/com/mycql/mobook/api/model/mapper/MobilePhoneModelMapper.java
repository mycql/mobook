package com.mycql.mobook.api.model.mapper;

import com.mycql.mobook.api.model.response.MobilePhoneModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MobilePhoneModelMapper {
    MobilePhoneModel entityToDto(com.mycql.mobook.entity.MobilePhoneModel source);
    com.mycql.mobook.entity.MobilePhoneModel dtoToEntity(MobilePhoneModel destination);
}

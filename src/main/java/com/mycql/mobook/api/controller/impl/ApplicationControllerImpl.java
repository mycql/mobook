package com.mycql.mobook.api.controller.impl;

import com.mycql.mobook.api.controller.ApplicationController;
import com.mycql.mobook.api.model.mapper.MobilePhoneBookingMapper;
import com.mycql.mobook.api.model.mapper.MobilePhoneMapper;
import com.mycql.mobook.api.model.request.MobilePhoneBookingSearchRequest;
import com.mycql.mobook.api.model.request.MobilePhoneCheckinRequest;
import com.mycql.mobook.api.model.request.MobilePhoneCheckoutRequest;
import com.mycql.mobook.api.model.request.MobilePhoneSearchRequest;
import com.mycql.mobook.api.model.response.MobilePhone;
import com.mycql.mobook.api.model.response.MobilePhoneBookingEntry;
import com.mycql.mobook.api.model.response.MobilePhoneCheckinResponse;
import com.mycql.mobook.api.model.response.MobilePhoneCheckoutResponse;
import com.mycql.mobook.entity.MobilePhoneBooking;
import com.mycql.mobook.exception.ResourceNotFoundException;
import com.mycql.mobook.service.MobilePhoneBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ApplicationControllerImpl implements ApplicationController  {

    private final MobilePhoneBookingService mobilePhoneBookingService;
    private final MobilePhoneMapper mobilePhoneMapper;
    private final MobilePhoneBookingMapper mobilePhoneBookingMapper;

    @Autowired
    public ApplicationControllerImpl(MobilePhoneBookingService mobilePhoneBookingService,
                                     MobilePhoneMapper mobilePhoneMapper,
                                     MobilePhoneBookingMapper mobilePhoneBookingMapper) {
        this.mobilePhoneBookingService = mobilePhoneBookingService;
        this.mobilePhoneMapper = mobilePhoneMapper;
        this.mobilePhoneBookingMapper = mobilePhoneBookingMapper;
    }

    @Override
    public MobilePhone checkMobilePhone(Long id) throws ResourceNotFoundException {
        return Optional.ofNullable(this.mobilePhoneBookingService.checkMobilePhone(id)).map(mobilePhoneMapper::entityToDto).get();
    }

    @Override
    public List<MobilePhone> searchMobilePhones(MobilePhoneSearchRequest searchRequest) {
        return this.mobilePhoneBookingService.searchMobilePhones(searchRequest)
            .stream()
            .map(mobilePhoneMapper::entityToDto)
            .collect(Collectors.toList());
    }

    @Override
    public MobilePhoneCheckoutResponse checkoutMobilePhone(MobilePhoneCheckoutRequest checkoutRequest) {
        var booking = this.mobilePhoneBookingService.checkout(checkoutRequest);
        var response = new MobilePhoneCheckoutResponse();
        response.setMobilePhone(mobilePhoneMapper.entityToDto(booking.getPhone()));
        response.setCheckoutReferenceId(booking.getId());
        response.setCheckoutTime(booking.getTimestamp());
        return response;
    }

    @Override
    public MobilePhoneCheckinResponse checkinMobilePhone(MobilePhoneCheckinRequest checkinRequest) {
        var booking = this.mobilePhoneBookingService.checkin(checkinRequest);
        var response = new MobilePhoneCheckinResponse();
        response.setCheckinReferenceId(booking.getId());
        response.setCheckinTime(booking.getTimestamp());
        return response;
    }

    @Override
    public MobilePhoneBookingEntry checkBooking(Long id) throws ResourceNotFoundException {
        return Optional.ofNullable(this.mobilePhoneBookingService.checkBooking(id)).map(mobilePhoneBookingMapper::entityToDto).get();
    }

    @Override
    public List<MobilePhoneBookingEntry> searchBookings(MobilePhoneBookingSearchRequest request) {
        return this.mobilePhoneBookingService.searchBookings(request)
            .stream()
            .map(mobilePhoneBookingMapper::entityToDto)
            .collect(Collectors.toList());
    }
}

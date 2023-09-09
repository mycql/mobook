package com.mycql.mobook.service;

import com.mycql.mobook.api.model.request.MobilePhoneBookingSearchRequest;
import com.mycql.mobook.api.model.request.MobilePhoneCheckinRequest;
import com.mycql.mobook.api.model.request.MobilePhoneCheckoutRequest;
import com.mycql.mobook.api.model.request.MobilePhoneSearchRequest;
import com.mycql.mobook.entity.MobilePhone;
import com.mycql.mobook.entity.MobilePhoneBooking;
import com.mycql.mobook.exception.ClientNotFoundException;
import com.mycql.mobook.exception.NoAvailableModelFoundException;
import com.mycql.mobook.exception.PhoneExistsException;
import com.mycql.mobook.exception.ResourceNotFoundException;

import java.util.List;

public interface MobilePhoneBookingService {

    MobilePhoneBooking checkin(MobilePhoneCheckinRequest request) throws PhoneExistsException, ClientNotFoundException;

    MobilePhoneBooking checkout(MobilePhoneCheckoutRequest request) throws NoAvailableModelFoundException, ClientNotFoundException;
    List<MobilePhone> searchMobilePhones(MobilePhoneSearchRequest filter);

    MobilePhone checkMobilePhone(Long id) throws ResourceNotFoundException;
    MobilePhoneBooking checkBooking(Long id) throws ResourceNotFoundException;

    List<MobilePhoneBooking> searchBookings(MobilePhoneBookingSearchRequest request);
}

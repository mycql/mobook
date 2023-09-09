package com.mycql.mobook.api.controller;

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
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
public interface ApplicationController {

    @GetMapping(
        value = "/mobile-phone/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    MobilePhone checkMobilePhone(@PathVariable Long id) throws ResourceNotFoundException;

    @PostMapping(
        value = "/mobile-phone/search",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<MobilePhone> searchMobilePhones(@RequestBody MobilePhoneSearchRequest searchRequest);

    @PostMapping(
        value = "/mobile-phone/checkout",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    MobilePhoneCheckoutResponse checkoutMobilePhone(@RequestBody MobilePhoneCheckoutRequest checkoutRequest);

    @PostMapping(
        value = "/mobile-phone/checkin",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    MobilePhoneCheckinResponse checkinMobilePhone(@RequestBody MobilePhoneCheckinRequest checkinRequest);

    @GetMapping(
        value = "/booking/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    MobilePhoneBookingEntry checkBooking(@PathVariable Long id) throws ResourceNotFoundException;

    @PostMapping(
        value = "/booking/search",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<MobilePhoneBookingEntry> searchBookings(@RequestBody MobilePhoneBookingSearchRequest request);

}

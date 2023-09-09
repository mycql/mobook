package com.mycql.mobook.service.impl;

import com.mycql.mobook.api.model.request.MobilePhoneBookingSearchRequest;
import com.mycql.mobook.api.model.request.MobilePhoneCheckinRequest;
import com.mycql.mobook.api.model.request.MobilePhoneCheckoutRequest;
import com.mycql.mobook.api.model.request.MobilePhoneSearchRequest;
import com.mycql.mobook.entity.*;
import com.mycql.mobook.exception.ClientNotFoundException;
import com.mycql.mobook.exception.NoAvailableModelFoundException;
import com.mycql.mobook.exception.PhoneExistsException;
import com.mycql.mobook.exception.ResourceNotFoundException;
import com.mycql.mobook.repository.MobilePhoneBookingClientRepository;
import com.mycql.mobook.repository.MobilePhoneBookingRepository;
import com.mycql.mobook.repository.MobilePhoneRepository;
import com.mycql.mobook.service.MobilePhoneBookingService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;

@Service
public class MobilePhoneBookingServiceImpl implements MobilePhoneBookingService {

    private final MobilePhoneRepository mobilePhoneRepository;
    private final MobilePhoneBookingClientRepository mobilePhoneBookingClientRepository;
    private final MobilePhoneBookingRepository mobilePhoneBookingRepository;

    @Autowired
    public MobilePhoneBookingServiceImpl(MobilePhoneRepository mobilePhoneRepository,
                                         MobilePhoneBookingClientRepository mobilePhoneBookingClientRepository,
                                         MobilePhoneBookingRepository mobilePhoneBookingRepository) {
        this.mobilePhoneRepository = mobilePhoneRepository;
        this.mobilePhoneBookingClientRepository = mobilePhoneBookingClientRepository;
        this.mobilePhoneBookingRepository = mobilePhoneBookingRepository;
    }

    @Transactional
    @Override
    public MobilePhoneBooking checkin(MobilePhoneCheckinRequest request) throws PhoneExistsException, ClientNotFoundException {
        var possibleClient = this.mobilePhoneBookingClientRepository.findById(request.getClientId());
        if (possibleClient.isEmpty()) {
            throw new ClientNotFoundException();
        }
        var checkedOutPhone = this.mobilePhoneRepository.findByIdAndStatus(request.getPhoneId(), MobilePhoneStatus.CHECKED_OUT);
        if (checkedOutPhone.isEmpty()) {
            throw new PhoneExistsException();
        }
        var toCheckIn = checkedOutPhone.get();
        toCheckIn.setStatus(MobilePhoneStatus.CHECKED_IN);
        this.mobilePhoneRepository.save(toCheckIn);
        MobilePhoneBooking booking = new MobilePhoneBooking();
        booking.setTimestamp(LocalDateTime.now(ZoneId.of("UTC")));
        booking.setPhone(toCheckIn);
        booking.setClient(possibleClient.get());
        booking.setAction(MobilePhoneBookingAction.CHECK_IN);
        booking.setStatus(MobilePhoneBookingStatus.ACCEPTED);
        this.mobilePhoneBookingRepository.save(booking);
        return booking;
    }

    @Transactional
    @Override
    public MobilePhoneBooking checkout(MobilePhoneCheckoutRequest request) throws NoAvailableModelFoundException, ClientNotFoundException {
        var possibleClient = this.mobilePhoneBookingClientRepository.findById(request.getClientId());
        if (possibleClient.isEmpty()) {
            throw new ClientNotFoundException();
        }
        var model = new MobilePhoneModel();
        model.setDevice(request.getDevice());
        model.setBrand(request.getBrand());
        model.setVersion(request.getVersion());
        var filter = new MobilePhone();
        filter.setModel(model);
        filter.setStatus(MobilePhoneStatus.CHECKED_IN);
        var availablePhones = this.mobilePhoneRepository.findMatching(filter, filter.getStatus());
        if (Objects.isNull(availablePhones) || availablePhones.isEmpty()) {
            throw new NoAvailableModelFoundException();
        } else {
            var toCheckout = availablePhones.get(0);
            toCheckout.setStatus(MobilePhoneStatus.CHECKED_OUT);
            mobilePhoneRepository.save(toCheckout);
            MobilePhoneBooking booking = new MobilePhoneBooking();
            booking.setTimestamp(LocalDateTime.now(ZoneId.of("UTC")));
            booking.setPhone(toCheckout);
            booking.setClient(possibleClient.get());
            booking.setAction(MobilePhoneBookingAction.CHECK_OUT);
            booking.setStatus(MobilePhoneBookingStatus.ACCEPTED);
            this.mobilePhoneBookingRepository.save(booking);
            return booking;
        }
    }

    @Override
    public List<MobilePhone> searchMobilePhones(MobilePhoneSearchRequest filter) {
        var modelFilter = new MobilePhoneModel();
        modelFilter.setBrand(filter.getBrand());
        modelFilter.setDevice(filter.getDevice());
        modelFilter.setVersion(filter.getVersion());
        var phoneFilter = new com.mycql.mobook.entity.MobilePhone();
        phoneFilter.setStatus(filter.getStatus());
        phoneFilter.setModel(modelFilter);

        return mobilePhoneRepository.findMatching(phoneFilter, phoneFilter.getStatus());
    }

    @Override
    public MobilePhone checkMobilePhone(Long id) {
        return this.mobilePhoneRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public MobilePhoneBooking checkBooking(Long id) {
        return this.mobilePhoneBookingRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public List<MobilePhoneBooking> searchBookings(MobilePhoneBookingSearchRequest request) {
        var result = this.mobilePhoneBookingRepository.findMatching(
                request);
        return result;
    }
}

package com.mycql.mobook.repository;

import com.mycql.mobook.api.model.request.MobilePhoneBookingSearchRequest;
import com.mycql.mobook.entity.MobilePhoneBooking;
import com.mycql.mobook.entity.MobilePhoneBookingAction;
import com.mycql.mobook.entity.MobilePhoneBookingStatus;
import com.mycql.mobook.entity.MobilePhoneStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MobilePhoneBookingRepository extends JpaRepository<MobilePhoneBooking, Long> {

    @Query(value = """
        SELECT mpb FROM MobilePhoneBooking mpb WHERE
            mpb.phone.id = :#{#filter.phoneId}
            OR (mpb.client.id = :#{#filter.clientId})
    """)
    List<MobilePhoneBooking> findMatching(MobilePhoneBookingSearchRequest filter);
}

package com.mycql.mobook.repository;

import com.mycql.mobook.entity.MobilePhoneBookingClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MobilePhoneBookingClientRepository extends JpaRepository<MobilePhoneBookingClient, Long> {
}

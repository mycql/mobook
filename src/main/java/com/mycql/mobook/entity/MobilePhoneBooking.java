package com.mycql.mobook.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "mobile_phone_booking")
public class MobilePhoneBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "action", nullable = false)
    private MobilePhoneBookingAction action;

    @Column(name = "status", nullable = false)
    private MobilePhoneBookingStatus status;

    @ManyToOne
    @JoinColumn(name = "mobile_phone_id", nullable = false)
    private MobilePhone phone;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private MobilePhoneBookingClient client;

}

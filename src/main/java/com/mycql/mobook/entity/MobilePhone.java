package com.mycql.mobook.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
@Entity
@Table(name = "mobile_phone")
public class MobilePhone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "status", nullable = false)
    private MobilePhoneStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id", nullable = false)
    private MobilePhoneModel model;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "phone")
    private List<MobilePhoneBooking> bookings;

}

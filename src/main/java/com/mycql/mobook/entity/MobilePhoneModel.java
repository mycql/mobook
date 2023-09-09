package com.mycql.mobook.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "mobile_phone_model")
public class MobilePhoneModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "device", nullable = false)
    private String device;

    @Column(name = "version")
    private String version;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "model")
    private List<MobilePhone> mobilePhones;
}

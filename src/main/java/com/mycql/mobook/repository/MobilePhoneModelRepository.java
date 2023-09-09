package com.mycql.mobook.repository;

import com.mycql.mobook.entity.MobilePhoneModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MobilePhoneModelRepository extends JpaRepository<MobilePhoneModel, Long> {
}

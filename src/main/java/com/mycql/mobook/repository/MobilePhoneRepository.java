package com.mycql.mobook.repository;

import com.mycql.mobook.entity.MobilePhone;
import com.mycql.mobook.entity.MobilePhoneStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MobilePhoneRepository extends JpaRepository<MobilePhone, Long> {

    Optional<MobilePhone> findByIdAndStatus(Long id, MobilePhoneStatus status);

    @Query(value = """
        SELECT mp FROM MobilePhone mp
            WHERE ((:#{#filter.model.brand} IS NULL) OR (mp.model.brand = :#{#filter.model.brand}))
            AND ((:#{#filter.model.device} IS NULL) OR (mp.model.device = :#{#filter.model.device}))
            AND ((:#{#filter.model.version} IS NULL) OR (mp.model.version = :#{#filter.model.version}))
            AND ((:status IS NULL) OR (mp.status = :status))
    """)
    List<MobilePhone> findMatching(@Param("filter") MobilePhone filter, @Param("status") MobilePhoneStatus status);
}

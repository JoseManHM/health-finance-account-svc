package com.healthaccountsvc.account.Repository;

import com.healthaccountsvc.account.Entities.Cuentas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public interface AccountRepository extends JpaRepository<Cuentas, Integer> {
    @Query(value = "SELECT NOW()", nativeQuery = true)
    Instant getCurrentTimestamp();
}

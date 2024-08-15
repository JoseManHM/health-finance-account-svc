package com.healthaccountsvc.account.Repository;

import com.healthaccountsvc.account.Entities.Cuentas;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public interface AccountRepository extends JpaRepository<Cuentas, Integer> {
    @Query(value = "SELECT NOW()", nativeQuery = true)
    Instant getCurrentTimestamp();

    @Query(value = "SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM cuenta a WHERE nombre = :nombre AND id_usuario = :id", nativeQuery = true)
    boolean existsAccountNameId(@Param("nombre") String nombre, @Param("id") int id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO cuenta (nombre, icono, cantidad, id_usuario) VALUES (:nombre, :icono, :cantidad, :usuario)", nativeQuery = true)
    void saveAccount(@Param("nombre") String nombre, @Param("icono") String icono, @Param("cantidad") float cantidad, @Param("usuario") int usuario);
}

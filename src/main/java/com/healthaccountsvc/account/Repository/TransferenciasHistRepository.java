package com.healthaccountsvc.account.Repository;

import com.healthaccountsvc.account.DTO.GetTransferHistoryDataProjection;
import com.healthaccountsvc.account.DTO.GetTransferInfoProjectionDTO;
import com.healthaccountsvc.account.Entities.TransferenciasHist;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferenciasHistRepository extends JpaRepository<TransferenciasHist, Integer> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO transferencias_hist (descripcion, id_origen, id_destino, id_usuario, monto) VALUES (:descripcion, :origen, :destino, :usuario, :monto)", nativeQuery = true)
    void addTransferenciaHist(@Param("descripcion") String descripcion, @Param("origen") int origen, @Param("destino") int destino, @Param("usuario") int usuario, @Param("monto") float monto);

    @Query(value = "SELECT a.id, a.descripcion, a.id_origen, c.nombre AS origen, a.id_destino, cuenta.nombre AS destino, a.id_usuario, a.monto, a.fecha_registro FROM Transferencias_hist AS a INNER JOIN cuenta AS c ON a.id_origen = c.id INNER JOIN Cuenta ON a.id_destino = Cuenta.id WHERE a.id_usuario = :id AND a.activo = 0 ORDER BY fecha_registro DESC;", nativeQuery = true)
    List<GetTransferHistoryDataProjection> getHistoryTransferData(@Param("id") int id);

    @Query(value = "SELECT id_origen, id_destino, monto FROM transferencias_hist th WHERE id = :id LIMIT 1", nativeQuery = true)
    GetTransferInfoProjectionDTO getInfoTransferHistory(@Param("id") int id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE transferencias_hist SET descripcion = :descripcion, id_origen = :origen, id_destino = :destino, monto = :monto WHERE id = :id AND id_usuario = :usuario", nativeQuery = true)
    void editTransferenciaHist(@Param("descripcion") String descripcion, @Param("origen") int origen, @Param("destino") int destino, @Param("monto") float monto, @Param("id") int id, @Param("usuario") int usuario);

    @Query(value = "SELECT CASE WHEN count(th) > 0 THEN true ELSE false END FROM transferencias_hist th WHERE id = :id AND id_usuario = :usuario AND activo = 0", nativeQuery = true)
    boolean existsTransferActive(@Param("id") int id, @Param("usuario") int usuario);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE transferencias_hist SET activo = 1 WHERE id = :id AND id_usuario = :usuario", nativeQuery = true)
    void eliminarTransferencia(@Param("id") int id, @Param("usuario") int usuario);
}

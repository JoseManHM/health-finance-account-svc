package com.healthaccountsvc.account.Entities;

import ch.qos.logback.core.joran.spi.DefaultClass;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cuentas {
    @Id
    private int id;
    @NotNull
    @ColumnDefault("")
    private String icono;
    @NotNull
    private float cantidad;
    @OneToOne
    @JoinColumn(name = "id")
    private Usuarios idUsuario;
    @NotNull
    @ColumnDefault("0")
    private int activo;
    @NotNull
    @CreatedDate
    private Instant fechaCreacion;
    @LastModifiedDate
    private Instant fechaModificacion;
}

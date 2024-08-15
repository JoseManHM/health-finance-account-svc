package com.healthaccountsvc.account.Entities;

import ch.qos.logback.core.joran.spi.DefaultClass;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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

@Entity(name = "cuenta")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cuentas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @NotEmpty
    private String nombre;
    @NotNull
    @ColumnDefault("")
    private String icono;
    @NotNull
    private float cantidad;
    @OneToOne
    @JoinColumn(name = "id")
    private Usuarios id_usuario;
    @NotNull
    @ColumnDefault("0")
    private int activo;
}

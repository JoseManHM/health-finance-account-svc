package com.healthaccountsvc.account.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountInfoTransferEditDTO {
    @JsonProperty("id")
    @NotNull
    @PositiveOrZero
    private int id;
    @JsonProperty("idOrigen")
    @NotNull
    @PositiveOrZero
    private int idOrigen;
    @JsonProperty("idDestino")
    @NotNull
    @PositiveOrZero
    private int idDestino;
    @JsonProperty("monto")
    @NotNull
    @PositiveOrZero
    private float monto;
    @JsonProperty("idUsuario")
    @NotNull
    @PositiveOrZero
    private int idUsuario;
    @JsonProperty("descripcion")
    @NotNull
    private String descripcion;
}

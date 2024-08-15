package com.healthaccountsvc.account.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountInfoAddDTO {
    @JsonProperty("nombre")
    @NotNull
    @NotEmpty
    private String nombre;
    @JsonProperty("icono")
    @NotNull
    private String icono;
    @JsonProperty("cantidad")
    @NotNull
    @PositiveOrZero
    private float cantidad;
    @JsonProperty("idUsuario")
    @NotNull
    @PositiveOrZero
    private int idUsuario;
}

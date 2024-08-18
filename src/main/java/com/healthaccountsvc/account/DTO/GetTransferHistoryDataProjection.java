package com.healthaccountsvc.account.DTO;

public interface GetTransferHistoryDataProjection {
    Long getId();
    String getDescripcion();
    Long getIdOrigen();
    String getOrigen();
    Long getIdDestino();
    String getDestino();
    Long getIdUsuario();
    Float getMonto();
    String getFechaRegistro();
}

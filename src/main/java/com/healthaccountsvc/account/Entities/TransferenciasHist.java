package com.healthaccountsvc.account.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Transferencias_hist")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferenciasHist {
    @Id
    private int id;
    private String descripcion;
    private int id_origen;
    private int id_destino;
    private int id_usuario;
    private float monto;
}

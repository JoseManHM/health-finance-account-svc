package com.healthaccountsvc.account.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuarios {
    @Id
    private int id;
    private String nombre;
    private String apellido;
    private String email;
    private int activo;
}

package com.healthaccountsvc.account.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseBasicDTO {
    private int status;
    private String mensaje;
}

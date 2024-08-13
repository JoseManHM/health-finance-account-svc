package com.healthaccountsvc.account.DTO;

import com.healthaccountsvc.account.Util.Meta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResponseDTO {
    private Meta meta;
    private Object data;
}

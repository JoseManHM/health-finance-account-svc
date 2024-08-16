package com.healthaccountsvc.account.Services;

import com.healthaccountsvc.account.DTO.AccountInfoAddDTO;
import com.healthaccountsvc.account.DTO.AccountInfoUpdateDTO;
import com.healthaccountsvc.account.DTO.ResponseBasicDTO;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    String pruebaConexion();
    ResponseBasicDTO agregarCuenta(AccountInfoAddDTO accountInfo);
    ResponseBasicDTO modificarCuenta(AccountInfoUpdateDTO accountInfo);
    ResponseBasicDTO eliminarCuenta(int id, int usuario);
}

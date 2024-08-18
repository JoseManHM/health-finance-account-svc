package com.healthaccountsvc.account.Services;

import com.healthaccountsvc.account.DTO.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
    String pruebaConexion();
    ResponseBasicDTO agregarCuenta(AccountInfoAddDTO accountInfo);
    ResponseBasicDTO modificarCuenta(AccountInfoUpdateDTO accountInfo);
    ResponseBasicDTO eliminarCuenta(int id, int usuario);
    ResponseBasicDTO addTransferenciaCuenta(AccountInfoTransferDTO accountInfo);
    List<GetAccountDataProjection> obtenerAllCuentas(int usuario);
    List<GetAccountDataProjection> obtenerAccount(int id, int usuario);
    List<GetTransferHistoryDataProjection> obtenerHisTransferencias(int usuario);
}

package com.healthaccountsvc.account.Services.Impl;

import com.healthaccountsvc.account.DTO.AccountInfoAddDTO;
import com.healthaccountsvc.account.DTO.ResponseBasicDTO;
import com.healthaccountsvc.account.Entities.Cuentas;
import com.healthaccountsvc.account.Entities.Usuarios;
import com.healthaccountsvc.account.Repository.AccountRepository;
import com.healthaccountsvc.account.Repository.UserRepository;
import com.healthaccountsvc.account.Services.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImpl implements AccountService {
    private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public String pruebaConexion(){
        String respuesta = null;
        try{
            respuesta = accountRepository.getCurrentTimestamp().toString();
        }catch (Exception e){
            log.error("Ocurrió un error: " + e.getMessage());
            respuesta = "Ocurrió un error: " + e.getMessage();
        }
        return respuesta;
    }

    @Override
    public ResponseBasicDTO agregarCuenta(AccountInfoAddDTO accountInfo){
        ResponseBasicDTO response = new ResponseBasicDTO();
        try{
            Usuarios userData = userRepository.findById(accountInfo.getIdUsuario()).orElse(null);
            if(userData != null){
                if(!accountRepository.existsAccountNameId(accountInfo.getNombre(), accountInfo.getIdUsuario())){
                    accountRepository.saveAccount(accountInfo.getNombre(), accountInfo.getIcono(), accountInfo.getCantidad(), userData.getId());
                    response.setStatus(1);
                    response.setMensaje("Cuenta agregada correctamente");
                }else{
                    response.setStatus(0);
                    response.setMensaje("Ya existe una cuenta asociada a ese usuario con ese nombre");
                }
            }else{
                response.setStatus(0);
                response.setMensaje("El usuario al que se quiere asociar la cuenta no existe");
            }
        }catch (Exception e){
            String error = "Ocurrio un error al crear la cuenta: " + e.getMessage();
            log.error(error);
            System.out.println(error);
            response.setStatus(-1);
            response.setMensaje(error);
        }
        return response;
    }
}

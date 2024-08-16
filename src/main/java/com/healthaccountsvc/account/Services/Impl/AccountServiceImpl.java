package com.healthaccountsvc.account.Services.Impl;

import com.healthaccountsvc.account.DTO.AccountInfoAddDTO;
import com.healthaccountsvc.account.DTO.AccountInfoUpdateDTO;
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

    @Override
    public ResponseBasicDTO modificarCuenta(AccountInfoUpdateDTO accountInfo){
        ResponseBasicDTO response = new ResponseBasicDTO();
        try{
            Usuarios userData = userRepository.findById(accountInfo.getIdUsuario()).orElse(null);
            if(userData != null){
                Cuentas cuentaData = accountRepository.findById(accountInfo.getId()).orElse(null);
                if(cuentaData != null){
                    accountRepository.updateAccount(accountInfo.getNombre(), accountInfo.getIcono(), accountInfo.getCantidad(), accountInfo.getId(), accountInfo.getIdUsuario());
                    response.setStatus(1);
                    response.setMensaje("La cuenta ha sido actualizada correctamente");
                }else{
                    response.setStatus(0);
                    response.setMensaje("La cuenta que se quiere modificar no existe");
                }
            }else{
                response.setStatus(0);
                response.setMensaje("El usuario asociado a la cuenta no existe");
            }
        }catch (Exception e){
            String error = "Ocurrio un error al modificar la cuenta: " + e.getMessage();
            log.error(error);
            System.out.println(error);
            response.setStatus(-1);
            response.setMensaje(error);
        }
        return response;
    }

    @Override
    public ResponseBasicDTO eliminarCuenta(int id, int usuario){
        ResponseBasicDTO response = new ResponseBasicDTO();
        try{
            Usuarios userExists = userRepository.findById(usuario).orElse(null);
            if(userExists != null){
                if(accountRepository.existsAccountActive(id, usuario)){
                    accountRepository.deleteAccount(id, usuario);
                    response.setStatus(1);
                    response.setMensaje("La cuenta se ha eliminado correctamente");
                }else{
                    response.setStatus(0);
                    response.setMensaje("La cuenta que se quiere eliminar no existe");
                }
            }else{
                response.setStatus(0);
                response.setMensaje("El usuario asociado a la cuenta no existe");
            }
        }catch (Exception e){
            String error = "Ocurrio un error al eliminar la cuenta: " + e.getMessage();
            log.error(error);
            System.out.println(error);
            response.setStatus(-1);
            response.setMensaje(error);
        }
        return response;
    }
}

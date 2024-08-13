package com.healthaccountsvc.account.Services.Impl;

import com.healthaccountsvc.account.Repository.AccountRepository;
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
}

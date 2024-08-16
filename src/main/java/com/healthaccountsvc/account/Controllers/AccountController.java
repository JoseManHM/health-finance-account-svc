package com.healthaccountsvc.account.Controllers;

import com.healthaccountsvc.account.DTO.AccountInfoAddDTO;
import com.healthaccountsvc.account.DTO.AccountInfoUpdateDTO;
import com.healthaccountsvc.account.DTO.ApiResponseDTO;
import com.healthaccountsvc.account.DTO.ResponseBasicDTO;
import com.healthaccountsvc.account.Services.AccountService;
import com.healthaccountsvc.account.Util.Meta;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    Meta metaOk = new Meta(UUID.randomUUID().toString(), "SUCCESS", 200);
    Meta metaNotFound = new Meta(UUID.randomUUID().toString(), "DATA NOT FOUND", 404);
    Meta metaServerError = new Meta(UUID.randomUUID().toString(), "INTERNAL SERVER ERROR", 500);

    @GetMapping("/test")
    public ApiResponseDTO testService(){
        return new ApiResponseDTO(metaOk, accountService.pruebaConexion());
    }

    @PostMapping("/cuenta")
    public ApiResponseDTO agregarCuenta(@RequestBody @Valid AccountInfoAddDTO accountInfo){
        ResponseBasicDTO response = accountService.agregarCuenta(accountInfo);
        if(response.getStatus() == 1){
            return new ApiResponseDTO(metaOk, response.getMensaje());
        }else if(response.getStatus() == 0){
            return new ApiResponseDTO(metaNotFound, response.getMensaje());
        }else{
            return new ApiResponseDTO(metaServerError, response.getMensaje());
        }
    }

    @PutMapping("/cuenta")
    public ApiResponseDTO modificarCuenta(@RequestBody @Valid AccountInfoUpdateDTO accountInfo){
        ResponseBasicDTO response = accountService.modificarCuenta(accountInfo);
        if(response.getStatus() == 1){
            return new ApiResponseDTO(metaOk, response.getMensaje());
        }else if(response.getStatus() == 0){
            return new ApiResponseDTO(metaNotFound, response.getMensaje());
        }else{
            return new ApiResponseDTO(metaServerError, response.getMensaje());
        }
    }

    @DeleteMapping("/cuenta/{id}/{usuario}")
    public ApiResponseDTO eliminarCuenta(@PathVariable(name = "id") int id, @PathVariable(name = "usuario") int usuario){
        ResponseBasicDTO response = accountService.eliminarCuenta(id, usuario);
        if(response.getStatus() == 1){
            return new ApiResponseDTO(metaOk, response.getMensaje());
        }else if(response.getStatus() == 0){
            return new ApiResponseDTO(metaNotFound, response.getMensaje());
        }else{
            return new ApiResponseDTO(metaServerError, response.getMensaje());
        }
    }
}

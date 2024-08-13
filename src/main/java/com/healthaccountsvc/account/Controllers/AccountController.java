package com.healthaccountsvc.account.Controllers;

import com.healthaccountsvc.account.DTO.ApiResponseDTO;
import com.healthaccountsvc.account.Services.AccountService;
import com.healthaccountsvc.account.Util.Meta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

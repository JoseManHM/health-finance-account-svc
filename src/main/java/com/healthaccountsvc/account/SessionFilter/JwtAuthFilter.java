package com.healthaccountsvc.account.SessionFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthaccountsvc.account.DTO.ApiResponseDTO;
import com.healthaccountsvc.account.Util.Meta;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthFilter implements Filter {
    @Autowired
    Environment env;

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;
        final ApiResponseDTO apiResponseDTO = new ApiResponseDTO();
        final ObjectMapper objectMapper = new ObjectMapper();
        String authHeader = req.getHeader(HttpHeaders.AUTHORIZATION);
        String uriToken = env.getProperty("authorization.validate.uri");
        if(authHeader == null){
            res.reset();
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            apiResponseDTO.setMeta(new Meta(null, "CLIENT_ERROR", 401, "No se cuenta con la autorización para acceder a este recurso"));
            res.getWriter().write(objectMapper.writeValueAsString(apiResponseDTO));
        }else{
            try{
                RestTemplate restTemplate = new RestTemplate();
                HttpHeaders headers = new HttpHeaders();
                headers.add("Authorization", req.getHeader("Authorization"));
                HttpEntity<String> requestApi = new HttpEntity<String>(headers);
                ResponseEntity<String> responseAPI = restTemplate.exchange(uriToken, HttpMethod.GET, requestApi, String.class);
                if(Objects.equals(responseAPI.getStatusCode().toString(), "200 OK")){
                    chain.doFilter(request, response);
                }else{
                    res.reset();
                    res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    res.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                    apiResponseDTO.setMeta(new Meta(null, "SERVER_ERROR", 500, "Ha ocurrido un error al validar el token, intente mas tarde"));
                    res.getWriter().write(objectMapper.writeValueAsString(apiResponseDTO));
                }
            }catch (Exception e){
                System.out.println("Falló la llamada a la API, " + e.getMessage());
                res.reset();
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                res.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                apiResponseDTO.setMeta(new Meta(null, "CLIENT_ERROR", 401, "No se cuenta con la autorización para acceder a este recurso"));
                res.getWriter().write(objectMapper.writeValueAsString(apiResponseDTO));
            }
        }
    }
}

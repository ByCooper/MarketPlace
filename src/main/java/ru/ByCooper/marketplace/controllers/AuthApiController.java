package ru.ByCooper.marketplace.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import ru.ByCooper.marketplace.dto.other.Credentials;

public interface AuthApiController {

    @Operation(summary = "Авторизация пользователя")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized"
            )
    })
    ResponseEntity<?> login(@RequestBody Credentials credentials);

    @Operation(summary = "Регистрация пользователя")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            )
    })
    ResponseEntity<?> register(@RequestBody Credentials credentials);
}
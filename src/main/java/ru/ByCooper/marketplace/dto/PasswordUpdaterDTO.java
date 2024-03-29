package ru.ByCooper.marketplace.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Setter;

@Setter
public class PasswordUpdaterDTO {

    @Schema(description = "Текущий пароль")
    public String currentPassword;

    @Schema(description = "Новый пароль")
    public String newPassword;
}
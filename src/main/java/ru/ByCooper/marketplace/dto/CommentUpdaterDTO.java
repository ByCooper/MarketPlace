package ru.ByCooper.marketplace.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentUpdaterDTO {

    @Schema(description = "Текст комментария")
    public String text;
}
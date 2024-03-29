package ru.ByCooper.marketplace.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

@Getter
public class CommentsDTO {

    @Schema(description = "Общее количество комментариев")
    public int count;

    @JsonProperty(value = "results")
    public List<CommentDTO> commentDTOS;

    public CommentsDTO(int count, List<CommentDTO> commentDTOS) {
        this.count = count;
        this.commentDTOS = commentDTOS;
    }
}
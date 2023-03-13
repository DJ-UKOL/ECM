package ru.dinerik.ECM.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Сущность поручения для запросов (приема)")
public class OrderForRequest {

    @NotBlank(message = "Поле Предмет поручения должно быть заполнено!")
    @Schema(description = "Предмет поручения")
    private String subject;

    @NotBlank(message = "Поле Срок исполнения поручения должно быть заполнено!")
    @Schema(description = "Срок исполнения поручения")
    private LocalDateTime timeExecution;
    @NotBlank(message = "Поле Текст поручения должно быть заполнено!")
    @Schema(description = "Текст поручения")
    private String text;
}
package ru.dinerik.ECM.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @Schema(description = "Срок исполнения поручения")
    private LocalDateTime timeExecution;
    @Schema(description = "Признак контрольности поручения")
    @NotNull
    private Boolean controlSign;
    @Schema(description = "Признак исполнения поручения")
    @NotNull
    private Boolean performanceSign;
    @NotBlank(message = "Поле Текст поручения должно быть заполнено!")
    @Schema(description = "Текст поручения")
    private String text;
}

package ru.dinerik.ECM.dto.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

// DTO для запросов (приема)
@Data
public class OrderForRequest {

    @NotBlank(message = "Поле Предмет поручения должно быть заполнено!")
    private String subject;                     // Предмет поручения
    private Long authorId;                      // Идентификатор автора
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime timeExecution;        // Срок исполнения
    @NotNull
    private Boolean controlSign = false;                // Признак контрольности
    @NotNull
    private Boolean performanceSign = false;            // Признак исполнения
    @NotBlank(message = "Поле Текст поручения должно быть заполнено!")
    private String text;                // Текст поручения

    private Set<Long> executorsId;
}

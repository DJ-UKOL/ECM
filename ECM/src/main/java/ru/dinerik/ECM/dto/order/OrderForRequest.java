package ru.dinerik.ECM.dto.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dinerik.ECM.statemachine.LeaveOrderState;

import java.time.LocalDateTime;

// DTO для запросов (приема)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderForRequest {

    @NotBlank(message = "Поле Предмет поручения должно быть заполнено!")
    private String subject;                         // Предмет поручения
    private LocalDateTime timeExecution;            // Срок исполнения
    @NotNull
    private Boolean controlSign;                    // Признак контрольности
    @NotNull
    private Boolean performanceSign;                // Признак исполнения
    @NotBlank(message = "Поле Текст поручения должно быть заполнено!")
    private String text;                            // Текст поручения
    private LeaveOrderState orderState;
}

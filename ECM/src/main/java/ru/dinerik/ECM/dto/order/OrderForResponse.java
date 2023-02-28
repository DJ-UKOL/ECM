package ru.dinerik.ECM.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// DTO для ответов (отображения)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderForResponse {

    private Long id;
    private String subject;                         // Предмет поручения
    private LocalDateTime timeExecution;            // Срок исполнения
    private Boolean controlSign;                    // Признак контрольности
    private Boolean performanceSign;                // Признак исполнения
    private String text;                            // Текст поручения
    private Long authorId;                          // Автор
    private String orderStateString;                // Статус поручения
}
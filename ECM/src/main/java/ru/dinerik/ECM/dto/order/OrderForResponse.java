package ru.dinerik.ECM.dto.order;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dinerik.ECM.dto.employee.EmployeeForResponse;

import java.time.LocalDateTime;
import java.util.List;

// DTO для ответов (отображения)
@Data
@NoArgsConstructor
public class OrderForResponse {

    private Long id;
    private String subject;                             // Предмет поручения
    private EmployeeForResponse author;                 // Автор
    private List<EmployeeForResponse> executors;        // Исполнители поручения
    private LocalDateTime timeExecution;               // Срок исполнения
    private Boolean controlSign;                        // Признак контрольности
    private Boolean performanceSign;                    // Признак исполнения
    private String text;                                // Текст поручения
    private String documentState;                       // Статус документа

    public OrderForResponse(Long id, String subject, Boolean controlSign, Boolean performanceSign, String text, String documentState) {
        this.id = id;
        this.subject = subject;
        this.controlSign = controlSign;
        this.performanceSign = performanceSign;
        this.text = text;
        this.documentState = documentState;
    }
}

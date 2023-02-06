package ru.dinerik.ECM.domain;

import lombok.Data;

// Поручение
@Data
public class Order {

    private String subject;             // Предмет поручения
    private String author;              // Автор
    private String executors;           // Исполнители поручения
    private String timeExecution;       // Срок исполнения
    private String controlSign;         // Признак контрольности
    private String performanceSign;     // Признак исполнения
    private String text;                // Текст поручения
}
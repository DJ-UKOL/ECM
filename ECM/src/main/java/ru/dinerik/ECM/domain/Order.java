package ru.dinerik.ECM.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dinerik.ECM.statemachine.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

// Поручение
@Data
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Поле Предмет поручения должно быть заполнено!")
    @Column
    private String subject;                         // Предмет поручения
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime timeExecution;            // Срок исполнения
    @NotNull
    private Boolean controlSign;            // Признак контрольности
    @NotNull
    private Boolean performanceSign;        // Признак исполнения
    @NotBlank(message = "Поле Текст поручения должно быть заполнено!")
    @Column
    private String text;                            // Текст поручения

    // Отношение многие к одному (много поручений у одного автора)
    @ManyToOne(fetch = FetchType.EAGER)
    private Employee author;                        // Автор

    // Отношение многие ко многим (у одного поручения много исполнителей, и у одного исполнителя много поручений)
    @ManyToMany(mappedBy = "orderExecutors", fetch = FetchType.EAGER)
    private Set<Employee> executors = new HashSet<>();           // Исполнители поручения

    @Enumerated
    private LeaveOrderState orderState;

    public Order(String subject, String text) {
        this.subject = subject;
        this.text = text;
    }
}
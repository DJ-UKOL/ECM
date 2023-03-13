package ru.dinerik.ECM.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import ru.dinerik.ECM.statemachine.LeaveOrderState;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// Поручение
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotBlank(message = "Поле Предмет поручения должно быть заполнено!")
    @Column
    private String subject;                         // Предмет поручения
    @NotNull
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime timeExecution;            // Срок исполнения
    @NotNull
    @NotBlank(message = "Поле Текст поручения должно быть заполнено!")
    @Column
    private String text;                            // Текст поручения

    // Отношение многие к одному (много поручений у одного автора)
    @ManyToOne(fetch = FetchType.EAGER)
    private Employee author;                        // Автор

    // Отношение многие ко многим (у одного поручения много исполнителей, и у одного исполнителя много поручений)
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "employees_orders",
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"))
    private Set<Employee> executors = new HashSet<>();           // Исполнители поручения

    @Enumerated(EnumType.ORDINAL)
    private LeaveOrderState orderState;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", timeExecution=" + timeExecution +
                ", text='" + text + '\'' +
                ", author=" + author +
                ", executors=" + executors +
                ", orderState=" + orderState +
                '}';
    }
}
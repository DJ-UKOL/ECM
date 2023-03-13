package ru.dinerik.ECM.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Objects;

// Подразделение
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "divisions")
public class Division {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Поле Наименование подразделения не должно быть пустым!")
    @Column
    private String fullName;            // Наименование подразделения
    @NotBlank(message = "Поле Контактные данные не должно быть пустым!")
    @Column
    private String contactDetails;      // Контактные данные

    // Отношение один ко многим (у одного руководителя может быть много подразделений)
    @ManyToOne
    private Employee manager;                   // Руководитель подразделения

    // Отношение одни ко многим (у одной организации много подразделений)
    @ManyToOne
    private Organization organization;            // Организация

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Division division = (Division) o;

        return Objects.equals(id, division.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Division{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", contactDetails='" + contactDetails + '\'' +
                ", manager=" + manager +
                ", organization=" + organization +
                '}';
    }
}
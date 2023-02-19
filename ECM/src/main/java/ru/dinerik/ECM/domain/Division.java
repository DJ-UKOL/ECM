package ru.dinerik.ECM.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

// Подразделение
@Data
@NoArgsConstructor
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

    public Division(String fullName, String contactDetails) {
        this.fullName = fullName;
        this.contactDetails = contactDetails;
    }
}
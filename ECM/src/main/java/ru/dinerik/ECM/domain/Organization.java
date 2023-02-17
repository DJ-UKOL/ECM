package ru.dinerik.ECM.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// Организация
@Data
@NoArgsConstructor
@Entity
@Table(name = "organizations")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Поле Наименование организации не должно быть пустым!")
    @Column
    private String fullName;            // Наименование организации
    @NotBlank(message = "Поле Физический адрес не должен быть пустым!")
    @Column
    private String postalAddress;       // Физический адрес
    @NotBlank(message = "Поле Юридический адрес не должен быть пустым!")
    @Column
    private String legalAddress;        // Юридический адрес

    // Отношение одни ко многим (у одного руководителя много организаций)
    @ManyToOne
    private Employee director;            // Руководитель

    // Отношение один ко многим (у одной организации много подразделений)
    @OneToMany(mappedBy = "organization")
    private List<Division> divisions;

    public Organization(String fullName, String postalAddress, String legalAddress) {
        this.fullName = fullName;
        this.postalAddress = postalAddress;
        this.legalAddress = legalAddress;
    }
}
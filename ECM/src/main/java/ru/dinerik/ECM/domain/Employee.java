package ru.dinerik.ECM.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

// Сотрудник
@Data
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Поле Фамилия должно быть заполнено!")
    @Column
    private String lastname;        // Фамилия
    @NotBlank(message = "Поле Имя должно быть заполнено!")
    @Column
    private String firstname;       // Имя
    @NotNull
    @Column
    private String patronymic;      // Отчество
    @NotBlank(message = "Поле Должность должно быть заполнено!")
    @Column
    private String jobTitle;        // Должность

    // Отношение один ко многим (у одного автора много поручение)
    @OneToMany(mappedBy = "author")
    private List<Order> orders;

    // Отношение один ко многим (один сотрудник владеет многими организациями)
    @OneToMany(mappedBy = "director")
    private List<Organization> organizations;

    // Отношение один ко многим (один сотрудник руководит многими подразделениями)
    @OneToMany(mappedBy = "manager")
    private List<Division> divisions;

    // Отношение многие ко многим (у одного поручения много исполнителей, и у одного исполнителя много поручений)
    @ManyToMany
    private Set<Order> orderExecutors;

    public Employee(String lastname, String firstname, String patronymic, String jobTitle) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.patronymic = patronymic;
        this.jobTitle = jobTitle;
    }
}

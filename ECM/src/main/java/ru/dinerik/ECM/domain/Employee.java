package ru.dinerik.ECM.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

// Сотрудник
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    @NotBlank(message = "Поле Отчество должно быть заполнено!")
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
    @ManyToMany(mappedBy = "executors", fetch = FetchType.EAGER)
    private Set<Order> orderExecutors = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        return Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", orders=" + orders +
                ", organizations=" + organizations +
                ", divisions=" + divisions +
                ", orderExecutors=" + orderExecutors +
                '}';
    }
}
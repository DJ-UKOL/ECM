package ru.dinerik.ECM.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;
import java.util.Objects;

// Организация
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", postalAddress='" + postalAddress + '\'' +
                ", legalAddress='" + legalAddress + '\'' +
                ", director=" + director +
                ", divisions=" + divisions +
                '}';
    }
}
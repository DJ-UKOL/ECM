package ru.dinerik.ECM.dto.employee;

import lombok.Data;
import lombok.NoArgsConstructor;

// DTO для ответов (отображения)
@Data
@NoArgsConstructor
public class EmployeeForResponse {

    private Long id;
    private String lastname;        // Фамилия
    private String firstname;       // Имя
    private String patronymic;      // Отчество
    private String jobTitle;        // Должность

    public EmployeeForResponse(Long id, String lastname, String firstname, String patronymic, String jobTitle) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.patronymic = patronymic;
        this.jobTitle = jobTitle;
    }
}

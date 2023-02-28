package ru.dinerik.ECM.dto.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO для ответов (отображения)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeForResponse {

    private Long id;
    private String lastname;        // Фамилия
    private String firstname;       // Имя
    private String patronymic;      // Отчество
    private String jobTitle;        // Должность
}
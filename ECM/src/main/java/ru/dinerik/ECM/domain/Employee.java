package ru.dinerik.ECM.domain;

import lombok.Data;

// Сотрудник
@Data
public class Employee {

    private String lastname;        // Фамилия
    private String firstname;       // Имя
    private String patronymic;      // Отчество
    private String jobTitle;        // Должность
}

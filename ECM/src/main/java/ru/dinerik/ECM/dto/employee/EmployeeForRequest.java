package ru.dinerik.ECM.dto.employee;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

// DTO для запросов (приема)
@Data
public class EmployeeForRequest {

    @NotBlank(message = "Поле Фамилия должно быть заполнено!")
    private String lastname;        // Фамилия
    @NotBlank(message = "Поле Имя должно быть заполнено!")
    private String firstname;       // Имя
    @NotNull
    private String patronymic;      // Отчество
    @NotBlank(message = "Поле Должность должно быть заполнено!")
    private String jobTitle;        // Должность

}

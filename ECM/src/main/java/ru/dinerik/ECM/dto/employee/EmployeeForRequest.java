package ru.dinerik.ECM.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Сущность сотрудника для запросов (приема)")
public class EmployeeForRequest {
    @NotBlank(message = "Поле Фамилия должно быть заполнено!")
    @Schema(description = "Фамилия сотрудника")
    private String lastname;
    @NotBlank(message = "Поле Имя должно быть заполнено!")
    @Schema(description = "Имя сотрудника")
    private String firstname;
    @NotNull
    @Schema(description = "Отчество сотрудника")
    private String patronymic;
    @NotBlank(message = "Поле Должность должно быть заполнено!")
    @Schema(description = "Должность сотрудника")
    private String jobTitle;
}
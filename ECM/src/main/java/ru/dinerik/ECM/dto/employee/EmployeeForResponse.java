package ru.dinerik.ECM.dto.employee;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Сущность сотрудника для ответов (отображения)")
public class EmployeeForResponse {

    @Schema(description = "Идентификатор сотрудника")
    private Long id;
    @Schema(description = "Фамилия сотрудника")
    private String lastname;
    @Schema(description = "Имя сотрудника")
    private String firstname;
    @Schema(description = "Отчество сотрудника")
    private String patronymic;
    @Schema(description = "Должность сотрудника")
    private String jobTitle;
}
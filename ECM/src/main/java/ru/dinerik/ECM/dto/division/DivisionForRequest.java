package ru.dinerik.ECM.dto.division;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

// DTO для запросов (приема)
@Data
public class DivisionForRequest {

    @NotBlank(message = "Наименование подразделения не должно быть пустым!")
    private String fullName;            // Наименование подразделения
    @NotBlank(message = "Контактные данные не должны быть пустыми!")
    private String contactDetails;      // Контактные данные
    private Long managerId;             // Идентификатор руководителя
}
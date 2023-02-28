package ru.dinerik.ECM.dto.division;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO для запросов (приема)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DivisionForRequest {
    @NotBlank(message = "Наименование подразделения не должно быть пустым!")
    private String fullName;            // Наименование подразделения
    @NotBlank(message = "Контактные данные не должны быть пустыми!")
    private String contactDetails;      // Контактные данные
}
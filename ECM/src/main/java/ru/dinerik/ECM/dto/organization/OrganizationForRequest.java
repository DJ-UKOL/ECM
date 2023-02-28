package ru.dinerik.ECM.dto.organization;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO для запросов (приема)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationForRequest {
    @NotBlank(message = "Поле Наименование организации не должно быть пустым!")
    private String fullName;            // Наименование организации
    @NotBlank(message = "Поле Физический адрес не должен быть пустым!")
    private String postalAddress;       // Физический адрес
    @NotBlank(message = "Поле Юридический адрес не должен быть пустым!")
    private String legalAddress;        // Юридический адрес
}
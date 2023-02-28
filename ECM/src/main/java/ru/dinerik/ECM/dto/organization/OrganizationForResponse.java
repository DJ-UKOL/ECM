package ru.dinerik.ECM.dto.organization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO для ответов (отображения)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationForResponse {
    private Long id;
    private String fullName;                         // Наименование организации
    private String postalAddress;                    // Физический адрес
    private String legalAddress;                     // Юридический адрес
    private Long directorId;                         // Руководитель
}
package ru.dinerik.ECM.dto.division;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO для ответов (отображения)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DivisionForResponse {
    private Long id;
    private String fullName;                            // Наименование подразделения
    private String contactDetails;                      // Контактные данные
    private Long managerId;                             // Руководитель
    private Long organizationId;                        // Организация
}
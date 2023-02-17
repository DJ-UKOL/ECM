package ru.dinerik.ECM.dto.division;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dinerik.ECM.dto.employee.EmployeeForResponse;
import ru.dinerik.ECM.dto.organization.OrganizationForResponse;

// DTO для ответов (отображения)
@Data
@NoArgsConstructor
public class DivisionForResponse {

    private Long id;
    private String fullName;                        // Наименование подразделения
    private String contactDetails;                  // Контактные данные
    private EmployeeForResponse manager;             // Руководитель
    private OrganizationForResponse organization;    // Организация

    public DivisionForResponse(Long id, String fullName, String contactDetails) {
        this.id = id;
        this.fullName = fullName;
        this.contactDetails = contactDetails;
    }
}
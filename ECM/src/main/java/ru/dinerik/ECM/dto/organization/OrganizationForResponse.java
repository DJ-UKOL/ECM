package ru.dinerik.ECM.dto.organization;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dinerik.ECM.dto.division.DivisionForResponse;
import ru.dinerik.ECM.dto.employee.EmployeeForResponse;

import java.util.List;

// DTO для ответов (отображения)
@Data
@NoArgsConstructor
public class OrganizationForResponse {
    private Long id;
    private String fullName;                         // Наименование организации
    private String postalAddress;                    // Физический адрес
    private String legalAddress;                     // Юридический адрес
    private EmployeeForResponse director;            // Руководитель
    private List<DivisionForResponse> divisions;        // Список подразделений

    public OrganizationForResponse(Long id, String fullName, String postalAddress, String legalAddress) {
        this.id = id;
        this.fullName = fullName;
        this.postalAddress = postalAddress;
        this.legalAddress = legalAddress;
    }
}
package ru.dinerik.ECM.dto.organization;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Сущность организации для ответов (отображения)")
public class OrganizationForResponse {

    @Schema(description = "Идентификатор организации")
    private Long id;
    @Schema(description = "Наименование организации")
    private String fullName;
    @Schema(description = "Физический адрес организации")
    private String postalAddress;
    @Schema(description = "Юридический адрес организации")
    private String legalAddress;
    @Schema(description = "Идентификатор директора организации")
    private Long directorId;
}
package ru.dinerik.ECM.dto.organization;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Сущность организации для запросов (приема)")
public class OrganizationForRequest {
    @NotBlank(message = "Поле Наименование организации не должно быть пустым!")
    @Schema(description = "Наименование организации")
    private String fullName;
    @NotBlank(message = "Поле Физический адрес не должен быть пустым!")
    @Schema(description = "Физический адрес организации")
    private String postalAddress;
    @NotBlank(message = "Поле Юридический адрес не должен быть пустым!")
    @Schema(description = "Юридический адрес организации")
    private String legalAddress;
}
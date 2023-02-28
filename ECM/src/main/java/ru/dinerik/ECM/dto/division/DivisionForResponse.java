package ru.dinerik.ECM.dto.division;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Сущность подразделения для ответов (отображения)")
public class DivisionForResponse {
    @Schema(description = "Идентификатор подразделения")
    private Long id;
    @Schema(description = "Наименование подразделения")
    private String fullName;
    @Schema(description = "Контактные данные подразделения")
    private String contactDetails;
    @Schema(description = "Идентификатор руководителя подразделения")
    private Long managerId;
    @Schema(description = "Идентификатор организации подразделения")
    private Long organizationId;
}
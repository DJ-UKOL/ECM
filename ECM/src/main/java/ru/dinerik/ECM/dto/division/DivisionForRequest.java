package ru.dinerik.ECM.dto.division;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Сущность подразделения для запросов (приема)")
public class DivisionForRequest {
    @NotBlank(message = "Наименование подразделения не должно быть пустым!")
    @Schema(description = "Наименование подразделения")
    private String fullName;
    @NotBlank(message = "Контактные данные не должны быть пустыми!")
    @Schema(description = "Контактные данные подразделения")
    private String contactDetails;
}
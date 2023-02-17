package ru.dinerik.ECM.mapper;

import ru.dinerik.ECM.domain.Division;
import ru.dinerik.ECM.dto.division.DivisionForRequest;
import ru.dinerik.ECM.dto.division.DivisionForResponse;

// Интерфейс для работы с маппером Подразделение
public interface DivisionMapper {

    // Mapper для запросов (приема)
    Division requestToDivision(DivisionForRequest source);

    // Mapper для ответов (отображения)
    DivisionForResponse responseToDivisionDto(Division source);
}
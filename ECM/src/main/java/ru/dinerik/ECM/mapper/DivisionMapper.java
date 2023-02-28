package ru.dinerik.ECM.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.dinerik.ECM.domain.Division;
import ru.dinerik.ECM.dto.division.DivisionForRequest;
import ru.dinerik.ECM.dto.division.DivisionForResponse;

// Интерфейс для работы с маппером Подразделение
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DivisionMapper {

    // Mapper для запросов (приема)
    Division requestToDivision(DivisionForRequest source);

    // Mapper для ответов (отображения)
    @Mapping(target = "organizationId", source = "source.organization.id")
    @Mapping(target = "managerId", source = "source.manager.id")
    DivisionForResponse responseToDivisionDto(Division source);
}
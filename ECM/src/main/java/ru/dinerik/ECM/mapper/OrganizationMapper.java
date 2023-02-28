package ru.dinerik.ECM.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.dinerik.ECM.domain.Organization;
import ru.dinerik.ECM.dto.organization.OrganizationForResponse;
import ru.dinerik.ECM.dto.organization.OrganizationForRequest;

// Интерфейс для работы с маппером Организация
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrganizationMapper {

    // Mapper для запросов (приема)
    Organization requestToOrganization(OrganizationForRequest source);

    // Mapper для ответов (отображения)
    @Mapping(target = "directorId", source = "source.director.id")
    OrganizationForResponse responseToOrganizationDto(Organization source);
}
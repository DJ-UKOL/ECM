package ru.dinerik.ECM.mapper;

import ru.dinerik.ECM.domain.Organization;
import ru.dinerik.ECM.dto.organization.OrganizationForResponse;
import ru.dinerik.ECM.dto.organization.OrganizationForRequest;

// Интерфейс для работы с маппером Организация
public interface OrganizationMapper {

    // Mapper для запросов (приема)
    Organization requestToOrganization(OrganizationForRequest source);

    // Mapper для ответов (отображения)
    OrganizationForResponse responseToOrganizationDto(Organization source);
}

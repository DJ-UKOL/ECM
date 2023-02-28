/*
package ru.dinerik.ECM.mapper.impl;

import org.springframework.stereotype.Component;
import ru.dinerik.ECM.domain.Organization;
import ru.dinerik.ECM.dto.organization.OrganizationForRequest;
import ru.dinerik.ECM.dto.organization.OrganizationForResponse;
import ru.dinerik.ECM.mapper.OrganizationMapper;

@Component
public class OrganizationMapperImpl implements OrganizationMapper {

    // Mapper для запросов (приема)
    @Override
    public Organization requestToOrganization(OrganizationForRequest source) {
        return new Organization(
                source.getFullName(),
                source.getPostalAddress(),
                source.getLegalAddress()
        );
    }

    // Mapper для ответов (отображения)
    @Override
    public OrganizationForResponse responseToOrganizationDto(Organization source) {
        OrganizationForResponse organizationForResponse = new OrganizationForResponse(
                source.getId(),
                source.getFullName(),
                source.getPostalAddress(),
                source.getLegalAddress()
        );

        if(source.getDirector() != null) {
            organizationForResponse.setDirector(new EmployeeMapperImpl().responseToEmployeeDto(source.getDirector()));
        }
        return organizationForResponse;
    }
}*/

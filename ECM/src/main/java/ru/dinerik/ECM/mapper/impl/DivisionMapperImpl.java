/*
package ru.dinerik.ECM.mapper.impl;

import org.springframework.stereotype.Component;
import ru.dinerik.ECM.domain.Division;
import ru.dinerik.ECM.dto.division.DivisionForRequest;
import ru.dinerik.ECM.dto.division.DivisionForResponse;
import ru.dinerik.ECM.mapper.DivisionMapper;

@Component
public class DivisionMapperImpl implements DivisionMapper {

    // Mapper для запросов (приема)
    @Override
    public Division requestToDivision(DivisionForRequest source){
        return new Division(
                source.getFullName(),
                source.getContactDetails()
        );
    }

    // Mapper для ответов (отображения)
    @Override
    public DivisionForResponse responseToDivisionDto(Division source){

        DivisionForResponse divisionForResponse = new DivisionForResponse(
                source.getId(),
                source.getFullName(),
                source.getContactDetails()
        );

        if(source.getManager() != null) {
            divisionForResponse.setManager(new EmployeeMapperImpl().responseToEmployeeDto(source.getManager()));
        }
        if(source.getOrganization() != null) {
            divisionForResponse.setOrganization(new OrganizationMapperImpl().responseToOrganizationDto(source.getOrganization()));
        }
        return divisionForResponse;
    }
}*/

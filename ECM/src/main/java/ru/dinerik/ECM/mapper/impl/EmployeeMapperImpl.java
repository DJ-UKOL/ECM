/*
package ru.dinerik.ECM.mapper.impl;

import org.springframework.stereotype.Component;
import ru.dinerik.ECM.domain.Employee;
import ru.dinerik.ECM.dto.employee.EmployeeForRequest;
import ru.dinerik.ECM.dto.employee.EmployeeForResponse;
import ru.dinerik.ECM.mapper.EmployeeMapper;

@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    // Mapper для запросов (приема)
    @Override
    public Employee requestToEmployee(EmployeeForRequest source) {
        return new Employee(
                source.getLastname(),
                source.getFirstname(),
                source.getPatronymic(),
                source.getJobTitle()
        );
    }

    // Mapper для ответов (отображения)
    @Override
    public EmployeeForResponse responseToEmployeeDto(Employee source) {
        return new EmployeeForResponse(
                source.getId(),
                source.getLastname(),
                source.getFirstname(),
                source.getPatronymic(),
                source.getJobTitle()
        );
    }
}*/

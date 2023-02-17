package ru.dinerik.ECM.mapper;

import ru.dinerik.ECM.domain.Employee;
import ru.dinerik.ECM.dto.employee.EmployeeForRequest;
import ru.dinerik.ECM.dto.employee.EmployeeForResponse;

// Интерфейс для работы с маппером Сотрудник
public interface EmployeeMapper {

    // Mapper для запросов (приема)
    Employee requestToEmployee(EmployeeForRequest source);

    // Mapper для ответов (отображения)
    EmployeeForResponse responseToEmployeeDto(Employee source);

}
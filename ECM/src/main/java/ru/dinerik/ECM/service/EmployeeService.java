package ru.dinerik.ECM.service;

import ru.dinerik.ECM.domain.Employee;

import java.util.List;
import java.util.Optional;

// Интерфейс для работы с сервисом Сотрудник
public interface EmployeeService {

    // Получить сотрудника по id
    Employee findById(Long id);

    // Получить список сотрудников с поиском по аттрибутам с пагинацией и сортировкой
    List<Employee> search(Optional<String> attribute,
                          Optional<String> searchText,
                          Optional<Integer> page,
                          Optional<Integer> employeePerPage,
                          Optional<String> sortBy);

    // Добавить нового сотрудника
    List<Employee> createEmployee(Employee employee);

    // Редактировать сотрудника
    List<Employee> updateEmployee(Long id, Employee employee);

    // Удалить сотрудника
    List<Employee> deleteEmployee(Long id);
}
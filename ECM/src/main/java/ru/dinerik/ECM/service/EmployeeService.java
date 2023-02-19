package ru.dinerik.ECM.service;

import ru.dinerik.ECM.domain.Employee;

import java.util.List;
import java.util.Optional;

// Интерфейс для работы с сервисом Сотрудник
public interface EmployeeService {

    // Получить список всех сотрудников с пагинацией и сортировкой
    List<Employee> findAll(Optional<Integer> page, Optional<Integer> employeePerPage, Optional<String> sortBy);

    // Получить сотрудника по id
    Employee findById(Long id);

    // Поиск сотрудника по аттрибутам
    List<Employee> search(Optional<String> attribute, Optional<String> searchText);

    // Добавить нового сотрудника
    List<Employee> createEmployee(Employee employee);

    // Редактировать сотрудника
    List<Employee> updateEmployee(Long id, Employee employee);

    // Удалить сотрудника
    List<Employee> deleteEmployee(Long id);
}
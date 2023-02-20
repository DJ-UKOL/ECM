package ru.dinerik.ECM.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dinerik.ECM.domain.Employee;
import ru.dinerik.ECM.repository.EmployeeRepository;
import ru.dinerik.ECM.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    // Получить список всех сотрудников с пагинацией и сортировкой
    @Override
    public List<Employee> findAll(Optional<Integer> page,
                                  Optional<Integer> employeePerPage,
                                  Optional<String> sortBy) {

        return page.isPresent() && employeePerPage.isPresent() ?
                sortBy.map(s -> repository
                        .findAll(PageRequest.of(page.get(), employeePerPage.get(), Sort.by(s)))
                        .getContent())
                    .orElseGet(() -> repository
                        .findAll(PageRequest.of(page.get(), employeePerPage.get()))
                        .getContent()) :
                sortBy.map(s -> repository
                        .findAll(Sort.by(s)))
                        .orElseGet(repository::findAll);
    }

    // Получить сотрудника по id
    @Override
    public Employee findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Сотрудник с номером id: " + id + " не найден"));
    }

    // Поиск сотрудника по аттрибутам с пагинацией и сортировкой
    @Override
    public List<Employee> search(Optional<String> attribute,
                                 Optional<String> searchText,
                                 Optional<Integer> page,
                                 Optional<Integer> employeePerPage,
                                 Optional<String> sortBy) {

        Pageable pageable = null;

        if(page.isPresent() && employeePerPage.isPresent())
            pageable = sortBy.map(s -> PageRequest.of(page.get(), employeePerPage.get(), Sort.by(s)))
                .orElseGet(() -> PageRequest.of(page.get(), employeePerPage.get()));

        if (attribute.isPresent() && searchText.isPresent()) {
            switch (attribute.get().toLowerCase()) {
                case "lastname" -> {
                    return repository.findAllByLastnameContainingIgnoreCase(pageable, searchText.get());
                }
                case "firstname" -> {
                    return repository.findAllByFirstnameContainingIgnoreCase(pageable, searchText.get());
                }
                case "patronymic" -> {
                    return repository.findAllByPatronymicContainingIgnoreCase(pageable, searchText.get());
                }
                case "jobtitle" -> {
                    return repository.findAllByJobTitleContainingIgnoreCase(pageable, searchText.get());
                }
            }
        }
        return findAll(page, employeePerPage, sortBy);
    }

    // Добавить нового сотрудника
    @Override
    @Transactional
    public List<Employee> createEmployee(Employee employee) {
        repository.save(employee);
        return repository.findAll();
    }

    // Редактировать сотрудника
    @Override
    @Transactional
    public List<Employee> updateEmployee(Long id, Employee employee) {
        findById(id);
        employee.setId(id);
        repository.save(employee);
        return repository.findAll();
    }

    // Удалить сотрудника
    @Override
    @Transactional
    public List<Employee> deleteEmployee(Long id) {
        findById(id);
        repository.deleteById(id);
        return repository.findAll();
    }
}
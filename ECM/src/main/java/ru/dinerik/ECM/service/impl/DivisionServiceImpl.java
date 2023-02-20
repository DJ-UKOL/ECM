package ru.dinerik.ECM.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dinerik.ECM.domain.Division;
import ru.dinerik.ECM.domain.Organization;
import ru.dinerik.ECM.repository.DivisionRepository;
import ru.dinerik.ECM.service.DivisionService;
import ru.dinerik.ECM.service.EmployeeService;
import ru.dinerik.ECM.service.OrganizationService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

// Бизнес логика
@Service
@Transactional(readOnly = true)
public class DivisionServiceImpl implements DivisionService {

    private final DivisionRepository repository;
    private final EmployeeService employeeService;
    private final OrganizationService organizationService;

    @Autowired
    public DivisionServiceImpl(DivisionRepository repository, EmployeeService employeeService, OrganizationService organizationService) {
        this.repository = repository;
        this.employeeService = employeeService;
        this.organizationService = organizationService;
    }

    // Получить список всех подразделений
    @Override
    public List<Division> findAll(Optional<Integer> page, Optional<Integer> divisionPerPage, Optional<String> sortBy) {
        if (page.isPresent() && divisionPerPage.isPresent()) {
            return sortBy.map(s -> repository.findAll(PageRequest.of(page.get(), divisionPerPage.get(), Sort.by(s))).getContent()).orElseGet(() -> repository.findAll(PageRequest.of(page.get(), divisionPerPage.get())).getContent());
        }
        else {
            return sortBy.map(s -> repository.findAll(Sort.by(s))).orElseGet(repository::findAll);
        }
    }

    // Получить подразделение по id
    @Override
    public Division findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Подразделение с номером id: " + id + " не найдено"));
    }

    // Поиск подразделения по аттрибутам с пагинацией и сортировкой
    @Override
    public List<Division> search(Optional<String> attribute,
                                 Optional<String> searchText,
                                 Optional<Integer> page,
                                 Optional<Integer> divisionPerPage,
                                 Optional<String> sortBy) {

        Pageable pageable = null;

        if (page.isPresent() && divisionPerPage.isPresent()) {
            pageable = sortBy.map(s -> PageRequest.of(page.get(), divisionPerPage.get(), Sort.by(s)))
                    .orElseGet(() -> PageRequest.of(page.get(), divisionPerPage.get()));
        }

        if (attribute.isPresent() && searchText.isPresent()) {
            switch (attribute.get().toLowerCase()) {
                case "fullname" -> {
                    return repository.findAllByFullNameContainingIgnoreCase(pageable, searchText.get());
                }
                case "contactdetails" -> {
                    return repository.findAllByContactDetailsContainingIgnoreCase(pageable, searchText.get());
                }
            }
        }
        return findAll(page, divisionPerPage, sortBy);
    }

    // Добавить новое подразделение
    @Override
    @Transactional
    public List<Division> createDivision(Division division) {
        repository.save(division);
        return repository.findAll();
    }

    // Назначить управляющего подразделением
    @Override
    @Transactional
    public Division assignManager(Long id, Long managerId) {
        findById(id).setManager(employeeService.findById(managerId));
        return findById(id);
    }

    // Прикрепить подразделение к организации
    @Override
    @Transactional
    public Division assignOrganization(Long id, Long organizationId) {
        findById(id).setOrganization(organizationService.findById(organizationId));
        return findById(id);
    }

    // Редактировать подразделение
    @Override
    @Transactional
    public List<Division> updateDivision(Long id, Division division) {
        findById(id);
        division.setId(id);
        repository.save(division);
        return repository.findAll();
    }

    // Удалить подразделение
    @Override
    @Transactional
    public List<Division> deleteDivision(Long id) {
        findById(id);
        repository.deleteById(id);
        return repository.findAll();
    }
}
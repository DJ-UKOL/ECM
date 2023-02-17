package ru.dinerik.ECM.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dinerik.ECM.domain.Organization;
import ru.dinerik.ECM.repository.OrganizationRepository;
import ru.dinerik.ECM.service.EmployeeService;
import ru.dinerik.ECM.service.OrganizationService;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

// Бизнес логика
@Service
@Transactional(readOnly = true)
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository repository;
    private final EmployeeService employeeService;

    @Autowired
    public OrganizationServiceImpl(OrganizationRepository repository, EmployeeService employeeService) {
        this.repository = repository;
        this.employeeService = employeeService;
    }

    // Получить список всех организаций
    @Override
    public List<Organization> findAll(Optional<Integer> page, Optional<Integer> organizationPerPage, Optional<String> sortBy) {
        if (page.isPresent() && organizationPerPage.isPresent()) {
            return sortBy.map(s -> repository
                            .findAll(PageRequest.of(page.get(), organizationPerPage.get(), Sort.by(s)))
                            .getContent())
                    .orElseGet(() -> repository
                            .findAll(PageRequest.of(page.get(), organizationPerPage.get()))
                            .getContent());
        }
        return sortBy.map(s -> repository.findAll(Sort.by(s))).orElseGet(repository::findAll);
    }

    // Получить организацию по id
    @Override
    public Organization findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Организация с номером id: " + id + " не найдена"));
    }

    // Добавить новую организацию
    @Override
    @Transactional
    public List<Organization> createOrganization(Organization organization) {
        repository.save(organization);
        return repository.findAll();
    }

    // Назначить директора организации
    @Override
    @Transactional
    public Organization assignDirector(Long id, Long directorId) {
        findById(id).setDirector(employeeService.findById(directorId));
        return findById(id);
    }

    // Редактировать организацию
    @Override
    @Transactional
    public List<Organization> updateOrganization(Long id, Organization organization) {
        findById(id);
        organization.setId(id);
        repository.save(organization);
        return repository.findAll();
    }

    // Удалить организацию
    @Override
    @Transactional
    public List<Organization> deleteOrganization(Long id) {
        findById(id);
        repository.deleteById(id);
        return repository.findAll();
    }
}
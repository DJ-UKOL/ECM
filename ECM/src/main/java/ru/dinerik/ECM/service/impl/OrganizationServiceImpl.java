package ru.dinerik.ECM.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dinerik.ECM.domain.Organization;
import ru.dinerik.ECM.repository.OrganizationRepository;
import ru.dinerik.ECM.service.EmployeeService;
import ru.dinerik.ECM.service.OrganizationService;
import ru.dinerik.ECM.service.impl.util.Sorting;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

// Бизнес логика
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository repository;
    private final EmployeeService employeeService;

    // Получить организацию по id
    @Override
    public Organization findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Организация с номером id: " + id + " не найдена"));
    }

    // Получить список организаций с поиском по аттрибутам с пагинацией и сортировкой
    @Override
    public List<Organization> search(Optional<String> attribute,
                                     Optional<String> searchText,
                                     Optional<Integer> page,
                                     Optional<Integer> organizationPerPage,
                                     Optional<String> sortBy) {

        if (attribute.isPresent() && searchText.isPresent()) {

            Pageable pageable = null;

            if(page.isPresent() && organizationPerPage.isPresent())
                pageable = sortBy.map(s -> PageRequest.of(page.get(), organizationPerPage.get(), Sort.by(s)))
                        .orElseGet(() -> PageRequest.of(page.get(), organizationPerPage.get()));

            switch (attribute.get().toLowerCase()) {
                case "fullname" -> {
                    return repository.findAllByFullNameContainingIgnoreCase(pageable, searchText.get());
                }
                case "postaladdress" -> {
                    return repository.findAllByPostalAddressContainingIgnoreCase(pageable, searchText.get());
                }
                case "legaladdress" -> {
                    return repository.findAllByLegalAddressContainingIgnoreCase(pageable, searchText.get());
                }
            }
        }
        Sorting<Organization> sorting = new Sorting<>(repository);
        return sorting.sortList(page, organizationPerPage, sortBy);
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
        Organization organization = findById(id);
        organization.setDirector(employeeService.findById(directorId));
        repository.save(organization);
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
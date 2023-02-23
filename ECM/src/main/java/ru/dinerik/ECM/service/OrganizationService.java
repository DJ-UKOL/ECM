package ru.dinerik.ECM.service;

import ru.dinerik.ECM.domain.Organization;

import java.util.List;
import java.util.Optional;

// Интерфейс для работы с сервисом Организация
public interface OrganizationService {

    // Получить организацию по id
    Organization findById(Long id);

    // Получить список организаций с поиском по аттрибутам с пагинацией и сортировкой
    List<Organization> search(Optional<String> attribute,
                              Optional<String> searchText,
                              Optional<Integer> page,
                              Optional<Integer> organizationPerPage,
                              Optional<String> sortBy);

    // Добавить новую организацию
    List<Organization> createOrganization(Organization organization);

    // Назначить директора организации
    Organization assignDirector(Long id, Long directorId);

    // Редактировать организацию
    List<Organization> updateOrganization(Long id, Organization organization);

    // Удалить организацию
    List<Organization> deleteOrganization(Long id);
}
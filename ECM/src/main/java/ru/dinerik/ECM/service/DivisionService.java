package ru.dinerik.ECM.service;

import ru.dinerik.ECM.domain.Division;

import java.util.List;
import java.util.Optional;

// Интерфейс для работы с сервисом Подразделение
public interface DivisionService {

    // Получить подразделение по id
    Division findById(Long id);

    // Получить список подразделений с поиском по аттрибутам с пагинацией и сортировкой
    List<Division> search(Optional<String> attribute,
                          Optional<String> searchText,
                          Optional<Integer> page,
                          Optional<Integer> divisionPerPage,
                          Optional<String> sortBy);

    // Добавить новое подразделение
    List<Division> createDivision(Division division);

    // Назначить управляющего подразделением
    Division assignManager(Long id, Long managerId);

    // Прикрепить подразделение к организации
    Division assignOrganization(Long id, Long organizationId);

    // Редактировать подразделение
    List<Division> updateDivision(Long id, Division division);

    // Удалить подразделение
    List<Division> deleteDivision(Long id);
}

package ru.dinerik.ECM.service;

import ru.dinerik.ECM.domain.Order;

import java.util.List;
import java.util.Optional;
import java.util.Set;

// Интерфейс для работы с сервисом Поручение
public interface OrderService {

    // Получить поручение по id
    Order findById(Long id);

    // Получить список поручений с поиском по аттрибутам с пагинацией и сортировкой
    List<Order> search(Optional<String> attribute,
                       Optional<String> searchText,
                       Optional<Integer> page,
                       Optional<Integer> orderPerPage,
                       Optional<String> sortBy);

    // Добавить новое поручение
    List<Order> createOder(Order order);

    // Передать документ на следующий этап
    Order assignPerformanceSign(Long id, Boolean performanceSign);

    // Назначить автора поручения
    Order assignAuthor(Long id, Long authorId);

    // Назначить исполнителей поручения
    Order assignExecutors(Long id, Set<Long> executorIds);

    // Редактировать поручение
    List<Order> updateOrder(Long id, Order order);

    // Удалить поручение
    List<Order> deleteOrder(Long id);
}
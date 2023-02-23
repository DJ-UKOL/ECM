package ru.dinerik.ECM.service;

import ru.dinerik.ECM.domain.Order;
import java.util.List;
import java.util.Optional;

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

    // Установить статус исполнение
    Order assignPerformanceSign(Long id, Boolean performanceSign);

    // Установить статус контроль
    Order assignControlSign(Long id, Boolean controlSign);

    // Редактировать поручение
    List<Order> updateOrder(Long id, Order order);

    // Удалить поручение
    List<Order> deleteOrder(Long id);
}
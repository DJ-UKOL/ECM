package ru.dinerik.ECM.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dinerik.ECM.domain.Order;
import ru.dinerik.ECM.repository.OrderRepository;
import ru.dinerik.ECM.service.OrderService;
import ru.dinerik.ECM.service.impl.util.Sorting;
import ru.dinerik.ECM.statemachine.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    // Получить поручение по id
    @Override
    public Order findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Поручение с номером id: " + id + " не найдено"));
    }

    // Получить список поручений с поиском по аттрибутам с пагинацией и сортировкой
    @Override
    public List<Order> search(Optional<String> attribute,
                              Optional<String> searchText,
                              Optional<Integer> page,
                              Optional<Integer> orderPerPage,
                              Optional<String> sortBy) {

        if (attribute.isPresent() && searchText.isPresent()) {

            Pageable pageable = null;

            if(page.isPresent() && orderPerPage.isPresent())
                pageable = sortBy.map(s -> PageRequest.of(page.get(), orderPerPage.get(), Sort.by(s)))
                        .orElseGet(() -> PageRequest.of(page.get(), orderPerPage.get()));

            switch (attribute.get().toLowerCase()) {
                case "subject" -> {
                    return repository.findAllBySubjectContainingIgnoreCase(pageable, searchText.get());
                }
                case "text" -> {
                    return repository.findAllByTextContainingIgnoreCase(pageable, searchText.get());
                }
            }
        }
        Sorting<Order> sorting = new Sorting<>(repository);
        return sorting.sortList(page, orderPerPage, sortBy);
    }

    // Добавить новое поручение
    @Override
    @Transactional
    public List<Order> createOder(Order order) {
        order.setOrderState(LeaveOrderState.PREPARE);         // Установить статус "Подготовка документа"
        repository.save(order);
        order.setOrderState(order.getOrderState().nextState(true));         // Установить статус "Подготовка документа"
        return repository.findAll();
    }

    // Установить статус исполнение
    @Override
    @Transactional
    public Order assignPerformanceSign(Long id, Boolean performanceSign) {
        Order order = findById(id);
        order.setPerformanceSign(performanceSign);
        repository.save(order);
        order.setOrderState(order.getOrderState().nextState(performanceSign));     // Установить статус "Документ проходит контроль"
        return findById(id);
    }

    // Установить статус контроль
    @Override
    @Transactional
    public Order assignControlSign(Long id, Boolean controlSign) {
        Order order = findById(id);
        if(!order.getPerformanceSign())
        {
            System.out.println("Контроль не может быть установлен, пока нет исполнения");
            return findById(id);
        }
        repository.save(order);
        order.setControlSign(controlSign);
        order.setOrderState(order.getOrderState().nextState(controlSign)); // Установить статус "Документ принят" или "Документ на доработке"
        return findById(id);
    }

    // Редактировать поручение
    @Override
    @Transactional
    public List<Order> updateOrder(Long id, Order order) {
        findById(id);
        order.setId(id);
        repository.save(order);
        return repository.findAll();
    }

    // Удалить поручение
    @Override
    @Transactional
    public List<Order> deleteOrder(Long id) {
        findById(id);
        repository.deleteById(id);
        return repository.findAll();
    }
}
package ru.dinerik.ECM.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dinerik.ECM.domain.Order;
import ru.dinerik.ECM.repository.OrderRepository;
import ru.dinerik.ECM.service.EmployeeService;
import ru.dinerik.ECM.service.OrderService;
import ru.dinerik.ECM.service.impl.util.Sorting;
import ru.dinerik.ECM.statemachine.LeaveOrderState;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final EmployeeService employeeService;

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
        order.setOrderState(LeaveOrderState.PREPARE);                               // Установить статус "Подготовка документа"
        order.setOrderState(order.getOrderState().nextState(true));          // Установить статус "Подготовка документа"
        repository.save(order);
        return repository.findAll();
    }

    // Передать документ на следующий этап
    @Override
    @Transactional
    public Order assignPerformanceSign(Long id, Boolean performanceSign) {
        Order order = findById(id);
        // Установить статус документа
        order.setOrderState(order.getOrderState().nextState(performanceSign));
        repository.save(order);
        return findById(id);
    }

    // Назначить автора поручения
    @Override
    @Transactional
    public Order assignAuthor(Long id, Long authorId) {
        Order order = findById(id);
        order.setAuthor(employeeService.findById(authorId));
        repository.save(order);
        return findById(id);
    }

    // Назначить исполнителей поручения
    @Override
    @Transactional
    public Order assignExecutors(Long id, Set<Long> executorIds) {
        Order order = findById(id);
        order.setExecutors(executorIds.stream().map(employeeService::findById).collect(Collectors.toSet()));
        repository.save(order);
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
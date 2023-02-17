package ru.dinerik.ECM.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dinerik.ECM.domain.Order;
import ru.dinerik.ECM.repository.OrderRepository;
import ru.dinerik.ECM.service.OrderService;
import ru.dinerik.ECM.statemachine.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    @Autowired
    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }

    // Получить список всех поручений
    @Override
    public List<Order> findAll(Optional<Integer> page, Optional<Integer> orderPerPage, Optional<String> sortBy) {
        if (page.isPresent() && orderPerPage.isPresent()) {
            return sortBy.map(s -> repository
                            .findAll(PageRequest.of(page.get(), orderPerPage.get(), Sort.by(s)))
                            .getContent())
                    .orElseGet(() -> repository
                            .findAll(PageRequest.of(page.get(), orderPerPage.get()))
                            .getContent());
        }
        return sortBy.map(s -> repository.findAll(Sort.by(s))).orElseGet(repository::findAll);
    }

    // Получить поручение по id
    @Override
    public Order findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Поручение с номером id: " + id + " не найдено"));
    }

    // Добавить новое поручение
    @Override
    @Transactional
    public List<Order> createOder(Order order) {
        order.setDocumentState(new PrepareState());         // Установить статус "Подготовка документа"
        repository.save(order);
        return repository.findAll();
    }

    // Установить статус исполнение
    @Override
    @Transactional
    public Order assignPerformanceSign(Long id, Boolean performanceSign) {
        Order order = findById(id);
        order.setPerformanceSign(performanceSign);
        if(performanceSign) {
            order.setDocumentState(new ControlState());     // Установить статус "Документ проходит контроль"
        } else {
            order.setDocumentState(new PerformState());     // Установить статус "Документ в работе"
        }
        repository.save(order);
        return findById(id);
    }

    // Установить статус контроль
    @Override
    @Transactional
    public Order assignControlSign(Long id, Boolean controlSign) {
        Order order = findById(id);
        order.setControlSign(controlSign);
        if(controlSign) {
            order.setDocumentState(new AcceptState());     // Установить статус "Документ принят"
        } else {
            order.setDocumentState(new ReformState());      // Установить статус "Документ на доработке"
        }
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
package ru.dinerik.ECM.mapper.impl;

import org.springframework.stereotype.Component;
import ru.dinerik.ECM.domain.Employee;
import ru.dinerik.ECM.domain.Order;
import ru.dinerik.ECM.dto.order.OrderForRequest;
import ru.dinerik.ECM.dto.order.OrderForResponse;
import ru.dinerik.ECM.mapper.EmployeeMapper;
import ru.dinerik.ECM.mapper.OrderMapper;

import java.util.stream.Collectors;

@Component
public class OrderMapperImpl implements OrderMapper {

    // Mapper для запросов (приема)
    @Override
    public Order requestToOrder(OrderForRequest source) {
        Order order =  new Order(
                source.getSubject(),
                source.getText()
        );

        if(source.getAuthorId() != null) {
            Employee employee = new Employee();
            employee.setId(source.getAuthorId());
            order.setAuthor(employee);
        }

        if(source.getTimeExecution() != null)
        {
            order.setTimeExecution(source.getTimeExecution());
        }

        order.setPerformanceSign(source.getPerformanceSign());

        order.setControlSign(source.getControlSign());

        return order;

    }

    // Mapper для ответов (отображения)
    @Override
    public OrderForResponse responseToOrderDto(Order source) {
        OrderForResponse orderForResponse = new OrderForResponse(
                source.getId(),
                source.getSubject(),
                source.getControlSign(),
                source.getPerformanceSign(),
                source.getText(),
                source.getOrderState().getStatus()
        );

        if(source.getAuthor() != null) {
            orderForResponse.setAuthor(new EmployeeMapperImpl().responseToEmployeeDto(source.getAuthor()));
        }

        if(!source.getExecutors().isEmpty())
        {
            EmployeeMapper mapper = new EmployeeMapperImpl();
            orderForResponse.setExecutors(source.getExecutors().stream().map(mapper::responseToEmployeeDto).collect(Collectors.toList()));
        }

        if(source.getTimeExecution() != null) {
            orderForResponse.setTimeExecution(source.getTimeExecution());
        }

        return orderForResponse;
    }
}
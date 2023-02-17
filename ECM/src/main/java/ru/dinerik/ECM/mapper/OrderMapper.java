package ru.dinerik.ECM.mapper;

import ru.dinerik.ECM.domain.Order;
import ru.dinerik.ECM.dto.order.OrderForRequest;
import ru.dinerik.ECM.dto.order.OrderForResponse;


// Интерфейс для работы с маппером Поручение
public interface OrderMapper {

    // Mapper для запросов (приема)
    Order requestToOrder(OrderForRequest source);

    // Mapper для ответов (отображения)
    OrderForResponse responseToOrderDto(Order source);

}

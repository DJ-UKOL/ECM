package ru.dinerik.ECM.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.dinerik.ECM.domain.Order;
import ru.dinerik.ECM.dto.order.OrderForRequest;
import ru.dinerik.ECM.dto.order.OrderForResponse;

// Интерфейс для работы с маппером Поручение
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {

    // Mapper для запросов (приема)
    Order requestToOrder(OrderForRequest source);

    // Mapper для ответов (отображения)
    @Mapping(target = "orderStateString", source = "source.orderState.status")
    @Mapping(target = "authorId", source = "source.author.id")
    OrderForResponse responseToOrderDto(Order source);
}
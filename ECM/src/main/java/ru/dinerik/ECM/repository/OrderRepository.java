package ru.dinerik.ECM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dinerik.ECM.domain.Order;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllBySubjectLikeIgnoreCase(String subject);
    List<Order> findAllByTextLikeIgnoreCase(String text);
}

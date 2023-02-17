package ru.dinerik.ECM.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dinerik.ECM.dto.order.OrderForRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderControllerTest {

/*    @Autowired
    private OrderController orderController;
    @Autowired
    private DivisionController divisionController;
    @Autowired
    private EmployeeController employeeController;
    @Autowired
    private OrganizationController organizationController;

    // Создать список из 100 поручений
    @Test
    void shouldCreateSuccessfully() {
        for (int i = 0; i < 100; i++) {
            OrderForRequest orderForRequest  = new OrderForRequest();
            orderForRequest.setSubject("New subject_" + i);
            orderForRequest.setText("Text for subject_" + i + "" + i +  "" + i + "" + i);
            orderController.createOrder(orderForRequest);
        }
        //assertEquals(100, (long) orderController.orderTable().size());
    }

    // Прикрепить подразделение к организации
    @Test
    void shouldAddOrganizationSuccessfully() {
        divisionController.assignOrganizationInDivision(2L, organizationController.findById(8L));
        assertEquals(8L, divisionController.findById(2L).getOrganization().getId());
    }*/

}
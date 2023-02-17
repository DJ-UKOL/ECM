package ru.dinerik.ECM.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dinerik.ECM.dto.division.DivisionForRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DivisionControllerTest {
    @Autowired
    private DivisionController divisionController;
    @Autowired
    private EmployeeController employeeController;
    @Autowired
    private OrganizationController organizationController;

    // Создать список из 100 подразделений
    @Test
    void shouldCreateSuccessfully() {
        for (int i = 0; i < 100; i++) {
            DivisionForRequest divisionForRequest  = new DivisionForRequest();
            divisionForRequest.setFullName("FullNameDivision_" + i);
            divisionForRequest.setContactDetails("Contact_" + i + "" + i +  "" + i + "" + i);
            divisionController.createDivision(divisionForRequest);
        }
        //assertEquals(100, (long) divisionController.divisionTable().size());
    }



    // Назначить управляющего подразделением
    @Test
    void shouldAddManagerSuccessfully() {
        divisionController.assignManagerInDivision(5L, employeeController.findById(7L));
        assertEquals(7L, divisionController.findById(5L).getManager().getId());
    }

    // Прикрепить подразделение к организации
    @Test
    void shouldAddOrganizationSuccessfully() {
        divisionController.assignOrganizationInDivision(2L, organizationController.findById(8L));
        assertEquals(8L, divisionController.findById(2L).getOrganization().getId());
    }
}
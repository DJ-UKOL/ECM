package ru.dinerik.ECM.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dinerik.ECM.dto.organization.OrganizationForRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrganizationControllerTest {
 /*   @Autowired
    private OrganizationController organizationController;
    @Autowired
    private EmployeeController employeeController;

    // Создать список из 100 организаций
    @Test
    void shouldCreateSuccessfully() {
        for (int i = 0; i < 100; i++) {
            OrganizationForRequest organizationForRequest  = new OrganizationForRequest();
            organizationForRequest.setFullName("OOO \"FirstName_" + i + "\"");
            organizationForRequest.setLegalAddress("Address_" + i + "" + i +  "" + i + "" + i);
            organizationForRequest.setPostalAddress("Address_" + i + "" + i);
            organizationController.createOrganization(organizationForRequest);
        }
        //assertEquals(100, (long) organizationController.organizationTable().size());
    }

    // Назначить Директора организации
    @Test
    void shouldAddDirectorSuccessfully() {
        organizationController.assignDirectorInOrganization(32L, employeeController.findById(10L));
        assertEquals(10L, organizationController.findById(32L).getDirector().getId());
    }*/
}
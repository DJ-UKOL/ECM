package ru.dinerik.ECM;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dinerik.ECM.controller.DivisionController;
import ru.dinerik.ECM.controller.EmployeeController;
import ru.dinerik.ECM.controller.OrderController;
import ru.dinerik.ECM.controller.OrganizationController;
import ru.dinerik.ECM.dto.division.DivisionForRequest;
import ru.dinerik.ECM.dto.division.DivisionForResponse;
import ru.dinerik.ECM.dto.employee.EmployeeForRequest;
import ru.dinerik.ECM.dto.employee.EmployeeForResponse;
import ru.dinerik.ECM.dto.order.OrderForRequest;
import ru.dinerik.ECM.dto.order.OrderForResponse;
import ru.dinerik.ECM.dto.organization.OrganizationForRequest;
import ru.dinerik.ECM.dto.organization.OrganizationForResponse;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EcmApplicationTests {

	@Autowired
	private EmployeeController employeeController;
	@Autowired
	private OrganizationController organizationController;
	@Autowired
	private DivisionController divisionController;
	@Autowired
	private OrderController orderController;

	@Test
	@Order(1)
	void createEmployee() {
		for (int i = 0; i < 100; i++) {
			EmployeeForRequest employeeForRequest  = new EmployeeForRequest();
			employeeForRequest.setFirstname("FirstName_" + i);
			employeeForRequest.setLastname("MyLastName_" + i);
			employeeForRequest.setPatronymic("Patr_" + i);
			employeeForRequest.setJobTitle("WorkIn_"+ i);
			employeeController.createEmployee(employeeForRequest);
		}
	}

	@Test
	@Order(1)
	void createOrganization() {
		for (int i = 0; i < 100; i++) {
			OrganizationForRequest organizationForRequest  = new OrganizationForRequest();
			organizationForRequest.setFullName("FullName_" + i);
			organizationForRequest.setPostalAddress("PostalAddress_" + i);
			organizationForRequest.setLegalAddress("LegalAddress_" + i);
			organizationController.createOrganization(organizationForRequest);
		}
	}

	@Test
	@Order(1)
	void createDivision() {
		for (int i = 0; i < 100; i++) {
			DivisionForRequest divisionForRequest  = new DivisionForRequest();
			divisionForRequest.setFullName("FullName_" + i);
			divisionForRequest.setContactDetails("Tel_" + i + " Address_" + i);
			divisionController.createDivision(divisionForRequest);
		}
	}

	@Test
	@Order(1)
	void createOrder() {
		for (int i = 0; i < 100; i++) {
			OrderForRequest orderForRequest  = new OrderForRequest();
			orderForRequest.setSubject("My Subject is _ " + i);
			orderForRequest.setText("Text order is _ " + i);
			orderForRequest.setTimeExecution(LocalDateTime.parse("2023-03-08T10:00:00.000"));
			orderController.createOrder(orderForRequest);
		}
	}

	@Test
	@Order(2)
	void shouldHaveEmployees() {
		assertEquals(100, (long) employeeController
				.employeeTable(java.util.Optional.empty(), java.util.Optional.empty(), java.util.Optional.empty(), java.util.Optional.empty(), java.util.Optional.empty())
				.size());
		assertEquals("FirstName_10", employeeController.findById(11L).getFirstname());
		assertEquals("MyLastName_55", employeeController.findById(56L).getLastname());
		assertEquals("Patr_79", employeeController.findById(80L).getPatronymic());
		assertEquals("WorkIn_33", employeeController.findById(34L).getJobTitle());
	}

	@Test
	@Order(2)
	void shouldHaveOrganizations() {
		assertEquals(100, (long) organizationController
				.organizationTable(java.util.Optional.empty(),
						java.util.Optional.empty(),
						java.util.Optional.empty(),
						java.util.Optional.empty(),
						java.util.Optional.empty())
				.size());
		assertEquals("FullName_10", organizationController.findById(11L).getFullName());
		assertEquals("PostalAddress_55", organizationController.findById(56L).getPostalAddress());
		assertEquals("LegalAddress_79", organizationController.findById(80L).getLegalAddress());
	}

	@Test
	@Order(2)
	void shouldHaveDivisions() {
		assertEquals(100, (long) divisionController
				.divisionTable(java.util.Optional.empty(),
						java.util.Optional.empty(),
						java.util.Optional.empty(),
						java.util.Optional.empty(),
						java.util.Optional.empty())
				.size());
		assertEquals("FullName_23", divisionController.findById(24L).getFullName());
		assertEquals("Tel_8 Address_8", divisionController.findById(9L).getContactDetails());
	}

	@Test
	@Order(2)
	void shouldHaveOrders() {
		assertEquals(100, (long) orderController
				.orderTable(java.util.Optional.empty(),
						java.util.Optional.empty(),
						java.util.Optional.empty(),
						java.util.Optional.empty(),
						java.util.Optional.empty())
				.size());
		assertEquals("My Subject is _ 44", orderController.findById(45L).getSubject());
		assertEquals("Text order is _ 78", orderController.findById(79L).getText());
		assertEquals(LocalDateTime.parse("2023-03-08T10:00:00.000"), orderController.findById(88L).getTimeExecution());
	}

	@Test
	@Order(3)
	void shouldUpdateEmployeeSuccessfully() {
		EmployeeForRequest employeeForRequest =
				new EmployeeForRequest("Васильев",
						"Василий", "Иванович", "Менеджер");
		employeeController.updateEmployee(5L, employeeForRequest);
		EmployeeForResponse employeeForResponse = employeeController.findById(5L);
		assertEquals("Васильев", employeeForResponse.getLastname());
		assertEquals("Василий", employeeForResponse.getFirstname());
		assertEquals("Иванович", employeeForResponse.getPatronymic());
		assertEquals("Менеджер", employeeForResponse.getJobTitle());
	}

	@Test
	@Order(3)
	void shouldUpdateOrganizationSuccessfully() {
		OrganizationForRequest organizationForRequest =
				new OrganizationForRequest("ООО Новые Команды",
						"Южный город на вершине гор", "Левее от лесополосы");
		organizationController.updateOrganization(17L, organizationForRequest);
		OrganizationForResponse organizationForResponse = organizationController.findById(17L);
		assertEquals("ООО Новые Команды", organizationForResponse.getFullName());
		assertEquals("Южный город на вершине гор", organizationForResponse.getPostalAddress());
		assertEquals("Левее от лесополосы", organizationForResponse.getLegalAddress());
	}

	@Test
	@Order(3)
	void shouldUpdateDivisionSuccessfully() {
		DivisionForRequest divisionForRequest =
				new DivisionForRequest("Отдел кадров",
						"Телефон: 2525б Адрес: тот же");
		divisionController.updateDivision(71L, divisionForRequest);
		DivisionForResponse divisionForResponse = divisionController.findById(71L);
		assertEquals("Отдел кадров", divisionForResponse.getFullName());
		assertEquals("Телефон: 2525б Адрес: тот же", divisionForResponse.getContactDetails());
	}

	@Test
	@Order(3)
	void shouldUpdateOrderSuccessfully() {
		OrderForRequest orderForRequest =
				new OrderForRequest("Договор 543221111", LocalDateTime.parse("2023-05-10T18:30:00.000"),
						"Выполнить срочно! Договор поставки, сроки горят!");
		orderController.updateOrder(29L, orderForRequest);
		OrderForResponse orderForResponse = orderController.findById(29L);
		assertEquals("Договор 543221111", orderForResponse.getSubject());
		assertEquals(LocalDateTime.parse("2023-05-10T18:30:00.000"), orderForResponse.getTimeExecution());
		assertEquals("Выполнить срочно! Договор поставки, сроки горят!", orderForResponse.getText());
	}

	@Test
	@Order(4)
	void assignDirectorSuccessfully() {
		organizationController.assignDirectorInOrganization(6L, 3L);
		assertEquals(3L, organizationController.findById(6L).getDirectorId());
	}

	@Test
	@Order(4)
	void assignManagerSuccessfully() {
		divisionController.assignManagerInDivision(16L, 1L);
		divisionController.assignManagerInDivision(78L, 56L);
		assertEquals(1L, divisionController.findById(16L).getManagerId());
		assertEquals(56L, divisionController.findById(78L).getManagerId());
	}

	@Test
	@Order(4)
	void assignOrganizationSuccessfully() {
		divisionController.assignOrganizationInDivision(60L, 32L);
		divisionController.assignOrganizationInDivision(45L, 12L);
		assertEquals(32L, divisionController.findById(60L).getOrganizationId());
		assertEquals(12L, divisionController.findById(45L).getOrganizationId());
	}

	@Order(4)
	@Test
	void assignAuthorSuccessfully() {
		orderController.assignAuthorInOrder(87L, 45L);
		orderController.assignAuthorInOrder(5L, 7L);
		assertEquals(45L, orderController.findById(87L).getAuthorId());
		assertEquals(7L, orderController.findById(5L).getAuthorId());
	}

	@Order(4)
	@Test
	void assignExecutorsSuccessfully() {
		Set<Long> executors1 = Set.of(4L, 5L, 7L);
		Set<Long> executors2 = Set.of(51L, 13L, 55L);
		orderController.assignExecutorInOrder(6L, executors1);
		orderController.assignExecutorInOrder(90L, executors2);
		assertEquals(executors1, orderController.findById(6L).getExecutorsIds());
		assertEquals(executors2, orderController.findById(90L).getExecutorsIds());
	}

	@Test
	@Order(5)
	void shouldDeleteEmployeeSuccessfully() {
		employeeController.deleteEmployee(78L);
		Throwable exception = assertThrows(NoSuchElementException.class, () -> employeeController.findById(78L));
		assertEquals("Сотрудник с номером id: 78 не найден", exception.getMessage());
	}

	@Order(5)
	@Test
	void shouldDeleteOrganizationSuccessfully() {
		organizationController.deleteOrganization(89L);
		Throwable exception = assertThrows(NoSuchElementException.class, () -> organizationController.findById(89L));
		assertEquals("Организация с номером id: 89 не найдена", exception.getMessage());
	}

	@Order(5)
	@Test
	void shouldDeleteDivisionSuccessfully() {
		divisionController.deleteDivision(33L);
		Throwable exception = assertThrows(NoSuchElementException.class, () -> divisionController.findById(33L));
		assertEquals("Подразделение с номером id: 33 не найдено", exception.getMessage());
		divisionController.deleteDivision(21L);
		exception = assertThrows(NoSuchElementException.class, () -> divisionController.findById(21L));
		assertEquals("Подразделение с номером id: 21 не найдено", exception.getMessage());
	}

	@Order(5)
	@Test
	void shouldDeleteOrderSuccessfully() {
		orderController.deleteOrder(15L);
		Throwable exception = assertThrows(NoSuchElementException.class,
				() -> orderController.findById(15L));
		assertEquals("Поручение с номером id: 15 не найдено", exception.getMessage());
	}

}
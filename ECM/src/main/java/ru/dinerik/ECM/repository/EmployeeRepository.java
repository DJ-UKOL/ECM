package ru.dinerik.ECM.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dinerik.ECM.domain.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAllByFirstnameContainingIgnoreCase(Pageable pageable, String firstname);
    List<Employee> findAllByLastnameContainingIgnoreCase(Pageable pageable, String lastname);
    List<Employee> findAllByPatronymicContainingIgnoreCase(Pageable pageable, String patronymic);
    List<Employee> findAllByJobTitleContainingIgnoreCase(Pageable pageable, String jobTitle);
}
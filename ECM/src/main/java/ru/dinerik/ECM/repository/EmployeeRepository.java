package ru.dinerik.ECM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dinerik.ECM.domain.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAllByFirstnameLikeIgnoreCase(String firstname);
    List<Employee> findAllByLastnameLikeIgnoreCase(String lastname);
    List<Employee> findAllByPatronymicLikeIgnoreCase(String patronymic);
    List<Employee> findAllByJobTitleLikeIgnoreCase(String jobTitle);
}
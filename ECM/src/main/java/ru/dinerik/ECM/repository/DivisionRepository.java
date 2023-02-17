package ru.dinerik.ECM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dinerik.ECM.domain.Division;

@Repository
public interface DivisionRepository extends JpaRepository<Division, Long> {

}
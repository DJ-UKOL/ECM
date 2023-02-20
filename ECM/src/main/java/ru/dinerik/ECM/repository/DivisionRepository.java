package ru.dinerik.ECM.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dinerik.ECM.domain.Division;

import java.util.List;

@Repository
public interface DivisionRepository extends JpaRepository<Division, Long> {

    List<Division> findAllByFullNameContainingIgnoreCase(Pageable pageable, String fullName);
    List<Division> findAllByContactDetailsContainingIgnoreCase(Pageable pageable, String contactDetails);
}
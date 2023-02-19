package ru.dinerik.ECM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dinerik.ECM.domain.Division;

import java.util.List;

@Repository
public interface DivisionRepository extends JpaRepository<Division, Long> {

    List<Division> findAllByFullNameLikeIgnoreCase(String fullName);
    List<Division> findAllByContactDetailsIgnoreCase(String contactDetails);
}
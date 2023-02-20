package ru.dinerik.ECM.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dinerik.ECM.domain.Organization;

import java.util.List;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    List<Organization> findAllByFullNameContainingIgnoreCase(Pageable pageable, String fullName);

    List<Organization> findAllByPostalAddressContainingIgnoreCase(Pageable pageable, String postalAddress);

    List<Organization> findAllByLegalAddressContainingIgnoreCase(Pageable pageable, String legalAddress);
}

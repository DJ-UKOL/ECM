package ru.dinerik.ECM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dinerik.ECM.domain.Organization;

import java.util.List;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    List<Organization> findAllByFullNameLikeIgnoreCase(String fullname);

    List<Organization> findAllByPostalAddressLikeIgnoreCase(String postalAddress);

    List<Organization> findAllByLegalAddressLikeIgnoreCase(String legalAddress);
}

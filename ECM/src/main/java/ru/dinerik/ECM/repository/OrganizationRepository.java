package ru.dinerik.ECM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dinerik.ECM.domain.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

}

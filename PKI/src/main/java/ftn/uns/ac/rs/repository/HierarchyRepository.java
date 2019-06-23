package ftn.uns.ac.rs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.uns.ac.rs.model.HierarchyModel;

@Repository
public interface HierarchyRepository extends JpaRepository<HierarchyModel, Long> {
	HierarchyModel findByComonName(String commonName);
}

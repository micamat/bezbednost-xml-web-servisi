package ftn.uns.ac.rs.SearchMicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import ftn.uns.ac.rs.SearchMicroservice.model.Smestaj;

@Repository
public interface SmestajRepository extends JpaRepository<Smestaj, Long>, JpaSpecificationExecutor<Smestaj> {

}

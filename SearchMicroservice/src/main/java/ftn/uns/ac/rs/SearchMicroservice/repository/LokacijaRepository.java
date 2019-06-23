package ftn.uns.ac.rs.SearchMicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.uns.ac.rs.SearchMicroservice.model.Lokacija;

@Repository
public interface LokacijaRepository extends JpaRepository<Lokacija, Long> {
	
}

package ftn.uns.ac.rs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.uns.ac.rs.model.Lokacija;

public interface LokacijaRepository extends JpaRepository<Lokacija, Long> {
	
}

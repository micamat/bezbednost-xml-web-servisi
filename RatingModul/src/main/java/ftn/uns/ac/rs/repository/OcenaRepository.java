package ftn.uns.ac.rs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.uns.ac.rs.model.Ocena;

@Repository
public interface OcenaRepository extends JpaRepository<Ocena, Long> {
	List<Ocena> findOcenaByRezervacijaSmestajId(Long id);
}
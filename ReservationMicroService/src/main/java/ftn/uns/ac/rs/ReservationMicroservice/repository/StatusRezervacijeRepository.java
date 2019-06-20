package ftn.uns.ac.rs.ReservationMicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.uns.ac.rs.ReservationMicroservice.model.StatusRezervacije;

@Repository
public interface StatusRezervacijeRepository extends JpaRepository<StatusRezervacije, Long> {

	StatusRezervacije findByNaziv(String naziv);
}

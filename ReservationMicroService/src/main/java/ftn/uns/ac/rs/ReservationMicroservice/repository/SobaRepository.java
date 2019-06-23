package ftn.uns.ac.rs.ReservationMicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.uns.ac.rs.ReservationMicroservice.model.Soba;

@Repository
public interface SobaRepository  extends JpaRepository<Soba, Long> {

}

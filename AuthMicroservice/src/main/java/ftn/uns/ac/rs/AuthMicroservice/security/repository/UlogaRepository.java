package ftn.uns.ac.rs.AuthMicroservice.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.uns.ac.rs.AuthMicroservice.security.model.Uloga;

@Repository
public interface UlogaRepository extends JpaRepository<Uloga, Long> {

}

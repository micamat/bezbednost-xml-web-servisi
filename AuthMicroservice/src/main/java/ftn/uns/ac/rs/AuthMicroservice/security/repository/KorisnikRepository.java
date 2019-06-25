package ftn.uns.ac.rs.AuthMicroservice.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.uns.ac.rs.AuthMicroservice.security.model.Korisnik;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {
	Korisnik findByKorisnickoIme(String korisnickoIme);
}

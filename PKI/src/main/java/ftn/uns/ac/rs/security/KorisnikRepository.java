package ftn.uns.ac.rs.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {
	Korisnik findByKorisnickoIme(String korisnickoIme);
}

package adminski.modul.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import adminski.modul.app.model.Korisnik;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long>{

}

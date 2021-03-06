package adminski.modul.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import adminski.modul.app.model.Komentar;

@Repository
public interface KomentarRepository extends JpaRepository<Komentar, Long>{

}

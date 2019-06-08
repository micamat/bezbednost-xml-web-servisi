package adminski.modul.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import adminski.modul.app.model.TipUsluge;

@Repository
public interface TipUslugeRepository extends JpaRepository<TipUsluge, Long>{

}

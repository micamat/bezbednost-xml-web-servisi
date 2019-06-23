package adminski.modul.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import adminski.modul.app.model.TipSmestaja;

@Repository
public interface TipSmestajaRepository extends JpaRepository<TipSmestaja, Long>{

}

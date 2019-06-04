package adminski.modul.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import adminski.modul.app.model.KategorijaSmestaja;

@Repository
public interface KategorijaSmestajaRepository extends JpaRepository<KategorijaSmestaja, Long>{

}

package agentski.modul.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import agentski.modul.app.model.soba.Soba;

@Repository
public interface SobaRepository extends JpaRepository<Soba, Long>{

}

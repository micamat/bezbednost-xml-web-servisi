package adminski.modul.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import adminski.modul.app.model.Agent;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long>{

}

package ftn.uns.ac.rs.AuthMicroservice.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.uns.ac.rs.AuthMicroservice.security.model.Agent;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
	public Agent findByKorisnickoIme(String korisnickoIme);
}

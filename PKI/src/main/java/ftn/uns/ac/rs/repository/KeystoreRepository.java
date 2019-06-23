package ftn.uns.ac.rs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.uns.ac.rs.model.Keystore;

@Repository
public interface KeystoreRepository extends JpaRepository<Keystore, Long> {
	
	boolean existsByKeystoreName(String keystoreName);
	Keystore findByKeystoreName(String keystoreName);
	
}

package project.certificate.keystore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.user.model.User;

@Repository
public interface KeystoreRepository extends JpaRepository<Keystore, Long> {
	
	boolean existsByKeystoreName(String keystoreName);
	User findByKeystoreName(String keystoreName);
	
}

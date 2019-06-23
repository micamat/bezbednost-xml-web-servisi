package ftn.uns.ac.rs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.uns.ac.rs.model.CertificateM;

@Repository
public interface CertificateRepository extends JpaRepository<CertificateM, Long>{
	public CertificateM findByAlias(String alias);
	
	boolean existsByAlias(String alias);
}

package project.certificate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.certificate.model.CertificateModel;

@Repository
public interface CertificateRepository extends JpaRepository<CertificateModel, Long>{
	
}

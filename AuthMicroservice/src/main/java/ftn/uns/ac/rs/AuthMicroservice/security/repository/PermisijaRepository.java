package ftn.uns.ac.rs.AuthMicroservice.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.uns.ac.rs.AuthMicroservice.security.model.Permisija;

@Repository
public interface PermisijaRepository extends JpaRepository<Permisija, Long> {

}

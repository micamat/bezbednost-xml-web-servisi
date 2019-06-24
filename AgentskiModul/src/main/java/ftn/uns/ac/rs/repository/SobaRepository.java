package ftn.uns.ac.rs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.uns.ac.rs.model.Soba;

@Repository
public interface SobaRepository extends JpaRepository<Soba, Long>{

}

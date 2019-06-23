package ftn.uns.ac.rs.SearchMicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.uns.ac.rs.SearchMicroservice.model.Usluga;

@Repository
public interface UslugaRepository extends JpaRepository<Usluga, Long> {

}

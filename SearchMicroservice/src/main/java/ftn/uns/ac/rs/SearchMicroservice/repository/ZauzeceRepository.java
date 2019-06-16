package ftn.uns.ac.rs.SearchMicroservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ftn.uns.ac.rs.SearchMicroservice.model.Zauzece;

@Repository
public interface ZauzeceRepository extends JpaRepository<Zauzece, Long>, JpaSpecificationExecutor<Zauzece> {
	@Query(value="SELECT soba.id FROM Zauzece WHERE soba.id IN :sobe AND id IN :zauzeca")
	public List<Long> findBySobaZauzece(@Param("sobe") List<Long> sobe, @Param("zauzeca") List<Long> zauzeca);
	
	@Query(value="SELECT soba.id FROM Zauzece")
	public List<Long> findSobe();
}

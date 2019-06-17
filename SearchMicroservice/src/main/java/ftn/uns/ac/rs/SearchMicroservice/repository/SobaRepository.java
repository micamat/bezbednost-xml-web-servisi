package ftn.uns.ac.rs.SearchMicroservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ftn.uns.ac.rs.SearchMicroservice.model.Soba;

@Repository
public interface SobaRepository extends JpaRepository<Soba, Long>, JpaSpecificationExecutor<Soba> {
	@Query(value = "SELECT soba.id FROM SobneUsluge WHERE soba.id = :soba AND usluga.id = :usluga")
	public Long findBySobaUsluga(@Param("soba") Long soba, @Param("usluga") Long usluga);
	
	@Query(value="SELECT id FROM Soba WHERE id NOT IN :sobeUZauzecu AND id IN :sobe")
	public List<Long> findBySobeNotZauzece(@Param("sobeUZauzecu") List<Long> sobeUZauzecu, @Param("sobe") List<Long> sobe);
	
	@Query(value="SELECT s FROM Soba s WHERE s.id IN :ids")
	public List<Soba> findAllSobe(@Param("ids") List<Long> ids);
}

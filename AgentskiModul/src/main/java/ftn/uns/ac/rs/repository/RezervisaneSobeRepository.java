package ftn.uns.ac.rs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.uns.ac.rs.model.RezervisaneSobe;
import ftn.uns.ac.rs.model.Soba;

@Repository
public interface RezervisaneSobeRepository extends JpaRepository<RezervisaneSobe, Long> {
	public List<RezervisaneSobe> findBySoba(Soba soba);
}

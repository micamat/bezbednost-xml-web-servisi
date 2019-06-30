package ftn.uns.ac.rs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.uns.ac.rs.model.Komentar;

@Repository
public interface KomentarRepository extends JpaRepository<Komentar, Long> {

	List<Komentar> findAllKomentarByStatusKomentara(String status);

}

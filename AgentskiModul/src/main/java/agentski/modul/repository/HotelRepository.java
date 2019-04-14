package agentski.modul.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import agentski.modul.model.HotelModel;

@Repository
public interface HotelRepository extends JpaRepository<HotelModel, Long>{

}

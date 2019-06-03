package adminski.modul.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import adminski.modul.app.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{

}

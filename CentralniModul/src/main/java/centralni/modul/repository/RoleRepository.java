package centralni.modul.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import centralni.modul.model.RoleModel;

public interface RoleRepository extends JpaRepository<RoleModel, Long>{
	
}

package centralni.modul.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import centralni.modul.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long>{
	
}

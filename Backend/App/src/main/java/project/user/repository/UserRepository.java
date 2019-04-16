package project.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByEmail(String email);
	
	boolean existsByUsername(String username);
	
	User findByEmail(String email);

	Optional<User> findByEmailAndPassword(String email, String password);
	
	User findByUsername(String username);
}
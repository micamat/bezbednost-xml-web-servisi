package centralni.modul.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import centralni.modul.model.PrivilegeModel;

public interface PrivilegeRepository extends JpaRepository<PrivilegeModel, Long>{

}

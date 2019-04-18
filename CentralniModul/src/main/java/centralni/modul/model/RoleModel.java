package centralni.modul.model;

import java.util.ArrayList;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

@Entity
public class RoleModel {
	
	public RoleModel() {
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false)
	String rolename;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
	  name = "role_privileges", 
	  joinColumns = @JoinColumn(name = "id"), 
	  inverseJoinColumns = @JoinColumn(name = "privilege_id"))
	Set<PrivilegeModel> privileges;
	
	public Set<PrivilegeModel> getPrivileges(){
		return privileges;
	}
	
	public void addPrivilege(PrivilegeModel privilege) {
		privileges.add(privilege);	
	}
	
	public Long getId() {
		return id;
	}
	
	public String getRolename() {
		return rolename;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
}

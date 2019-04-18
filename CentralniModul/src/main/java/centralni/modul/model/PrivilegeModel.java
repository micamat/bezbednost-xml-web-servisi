package centralni.modul.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="privilege_model")
public class PrivilegeModel {
	public PrivilegeModel() {
	}
	
	public PrivilegeModel(String privilegename) {
		this.privilegename = privilegename;
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long privilege_id;
	
	@Column(nullable = false)
	String privilegename;
	
	@ManyToMany(mappedBy = "privileges", fetch=FetchType.EAGER)
	Set<RoleModel> roles;
	
	public Long getPrivilege_id() {
		return privilege_id;
	}
	
	public String getPrivilegename() {
		return privilegename;
	}
	
	public void setPrivilege_id(Long privilege_id) {
		this.privilege_id = privilege_id;
	}
	
	public void setPrivilegename(String privilegename) {
		this.privilegename = privilegename;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PrivilegeModel other = (PrivilegeModel) obj;
		if (privilegename == null) {
			if (other.privilegename != null)
				return false;
		} else if (!privilegename.equals(other.privilegename))
			return false;
		return true;
	}
	
	
}

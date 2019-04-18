package centralni.modul.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class UserModel{
	
	public UserModel() {
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false)
	String email;
	
	@Column(nullable = false)
	String password;
	
	@Column(nullable = false)
	String Ime;
	
	@Column(nullable = false)
	String Prezime;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_role", referencedColumnName = "id")
	RoleModel role;
	
	public RoleModel getRole() {
		return role;
	}
	
	public void setRole(RoleModel role) {
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIme() {
		return Ime;
	}

	public void setIme(String ime) {
		Ime = ime;
	}

	public String getPrezime() {
		return Prezime;
	}

	public void setPrezime(String prezime) {
		Prezime = prezime;
	}
	
	public boolean hasPrivilege(String privilegename) {
		for(PrivilegeModel privilege : this.getRole().privileges) {
			if (privilege.privilegename.equals(privilegename)) {
				return true;
			}
		}
		return false;
	}
}

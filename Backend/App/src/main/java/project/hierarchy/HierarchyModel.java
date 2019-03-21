package project.hierarchy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hierarchy")
public class HierarchyModel {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false)
	private String comonName;
	
	@Column(nullable = false)
	private String surname;
	
	@Column(nullable = false)
	private String givenName;
	
	@Column(nullable = false)
	private String organizationName;
	
	@Column(nullable = false)
	private String organizationUnitName;
	
	@Column(nullable = false)
	private String countryName;
	
	@Column(nullable = false)
	private String email;
	
	private String path;
	
	private Long parentId;
	
	public HierarchyModel() {
		super();
	}
	
	public HierarchyModel(String comonName, String surname, String givenName, String organizationName,
			String organizationUnitName, String countryName, String email, Long parentId) {
		super();
		this.comonName = comonName;
		this.surname = surname;
		this.givenName = givenName;
		this.organizationName = organizationName;
		this.organizationUnitName = organizationUnitName;
		this.countryName = countryName;
		this.email = email;
		this.parentId = parentId;
	}

	public String getComonName() {
		return comonName;
	}
	public void setComonName(String comonName) {
		this.comonName = comonName;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public String getOrganizationUnitName() {
		return organizationUnitName;
	}
	public void setOrganizationUnitName(String organizationUnitName) {
		this.organizationUnitName = organizationUnitName;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public Long getId() {
		return id;
	}
	@Override
	public String toString() {
		return "HierarchyModel [id=" + id + ", comonName=" + comonName + ", surname=" + surname + ", givenName="
				+ givenName + ", organizationName=" + organizationName + ", organizationUnitName="
				+ organizationUnitName + ", countryName=" + countryName + ", email=" + email + ", path=" + path
				+ ", parentId=" + parentId + "]";
	}
	
	
}

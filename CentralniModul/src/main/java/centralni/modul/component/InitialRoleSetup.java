package centralni.modul.component;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import centralni.modul.model.PrivilegeModel;
import centralni.modul.model.RoleModel;
import centralni.modul.repository.PrivilegeRepository;
import centralni.modul.repository.RoleRepository;
import centralni.modul.repository.UserRepository;

@Component
public class InitialRoleSetup implements ApplicationListener<ContextRefreshedEvent> {

	boolean alreadySetup = false;
	
	@Autowired
    private UserRepository userRepository;
  
    @Autowired
    private RoleRepository roleRepository;
  
    @Autowired
    private PrivilegeRepository privilegeRepository;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (alreadySetup == false) {
			ArrayList<String> initialRoles = new ArrayList<>();
			initialRoles.add("user");
			initialRoles.add("agent");
			initialRoles.add("admin");
			
			setupRoles(initialRoles);
			
			ArrayList<String> initialPrivileges = new ArrayList<>();
			initialPrivileges.add("READ_USERS");
			
			setupPrivileges(initialPrivileges);
			
			assignPrivileges("admin", new String[] {"READ_USERS"});
			assignPrivileges("agent", new String[] {"READ_USERS"});
			
			alreadySetup = true;
		}
	}

	private void assignPrivileges(String rolename, String[] privileges) {
		RoleModel role = null;
		
		for(RoleModel rm : roleRepository.findAll()) {
			if (rm.getRolename().equals(rolename)) {
				role = rm;
				break;
			}
		}
		
		if (role != null) {
			for (String privilege : privileges) {
				for (PrivilegeModel pm : privilegeRepository.findAll()) {
					if (pm.getPrivilegename().equals(privilege)) {
						role.addPrivilege(pm);
						break;
					}
				}
			}
			
			roleRepository.save(role);
		}
	}

	private void setupPrivileges(ArrayList<String> initialPrivileges) {
		for (String privilege : initialPrivileges) {
			
			boolean found = false;
			
			for (PrivilegeModel pm : privilegeRepository.findAll()) {
				if (pm.getPrivilegename().equals(privilege)) {
					found = true;
					break;
				}
			}
			
			if (found == false) {
				PrivilegeModel privilegeModel = new PrivilegeModel();
				privilegeModel.setPrivilegename(privilege);
				privilegeRepository.save(privilegeModel);
			}
		}
	}
	
	private void setupRoles(ArrayList<String> initialRoles) {
		for (String role : initialRoles) {
			
			boolean found = false;
			
			for (RoleModel rm : roleRepository.findAll()) {
				if (rm.getRolename().equals(role)) {
					found = true;
					break;
				}
			}
			
			if (found == false) {
				RoleModel roleModel = new RoleModel();
				roleModel.setRolename(role);
				roleRepository.save(roleModel);
			}
		}
	}

}

package ftn.uns.ac.rs.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ac.rs.model.HierarchyModel;
import ftn.uns.ac.rs.repository.HierarchyRepository;
import ftn.uns.ac.rs.security.Username;

@Service
public class HierarchyService {
	
	@Autowired
	HierarchyRepository repository;
	private Logger logger = LogManager.getLogger();

	private static final Marker USER = MarkerManager.getMarker("USER");

	
	public HierarchyModel findById(long id) {
		return repository.findById(id).get();
	}
	
	public List<HierarchyModel> findAll(){
		return repository.findAll();
	}
	
	public HierarchyModel save(HierarchyModel node) {
		ThreadContext.put("user", Username.getLoggedUser());
		System.out.println("NODE: " + node);
		if(node.getParentId() != null) {
			node.setPath(repository.findById(node.getParentId()).get().getPath().concat("/").concat(node.getComonName()));
		}
		else {
			node.setPath(node.getComonName());
		}
		logger.info(USER, "Dodat cvor unutar hijerarhije");
		return repository.save(node);
	}
	
	public List<HierarchyModel> findChildren(Long nodeId){
		HierarchyModel node = repository.findById(nodeId).get();
		List<HierarchyModel> ret = new ArrayList<HierarchyModel>();
		for (HierarchyModel hm : repository.findAll()) {
			if(hm.getPath().startsWith(node.getPath())) {
				if(!hm.getPath().equals(node.getPath()))
					ret.add(hm);
			}
		}
		return ret;
	}
	
	public HierarchyModel findByComonName(String comonName) {
		return repository.findByComonName(comonName);
	}
}

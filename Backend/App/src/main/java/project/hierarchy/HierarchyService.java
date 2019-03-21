package project.hierarchy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HierarchyService {
	
	@Autowired
	HierarchyRepository repository;
	
	public HierarchyModel findById(long id) {
		return repository.findById(id).get();
	}
	
	public List<HierarchyModel> findAll(){
		return repository.findAll();
	}
	
	public HierarchyModel save(HierarchyModel node) {
		System.out.println("NODE: " + node);
		if(node.getParentId() != null) {
			node.setPath(repository.findById(node.getParentId()).get().getPath().concat("/").concat(node.getComonName()));
		}
		else {
			node.setPath(node.getComonName());
		}
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
}

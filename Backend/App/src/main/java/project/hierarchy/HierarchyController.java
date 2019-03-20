package project.hierarchy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hierarchies")
public class HierarchyController {
	@Autowired
	HierarchyService service;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HierarchyModel> findById(Long id){
		return new ResponseEntity<HierarchyModel>(service.findById(id), HttpStatus.FOUND);
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<HierarchyModel>> findAll(){
		return new ResponseEntity<List<HierarchyModel>>(service.fidAll(), HttpStatus.FOUND);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HierarchyModel> save(HierarchyModel node){
		return new ResponseEntity<HierarchyModel>(service.save(node), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/children", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<HierarchyModel>> findChildren(Long node){
		return new ResponseEntity<List<HierarchyModel>>(service.findChildren(node), HttpStatus.FOUND);
	}
}

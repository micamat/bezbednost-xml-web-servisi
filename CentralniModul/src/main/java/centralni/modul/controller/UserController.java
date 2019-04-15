package centralni.modul.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import centralni.modul.model.UserModel;
import centralni.modul.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/all")
	public ResponseEntity<List<UserModel>> findAll(){
		return new ResponseEntity<List<UserModel>>(userService.findAll(), HttpStatus.OK);
	}
}
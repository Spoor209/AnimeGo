package com.udemy.controllers;
/**
 * 
 * @author adlenkhelladi
 *
 */



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.entities.User;
import com.udemy.repositeries.IUser;


//@RestController is a stereotype annotation that combines @ResponseBody and @Controller.

@RestController
@CrossOrigin(origins="*", maxAge = 3600)
@RequestMapping("/v1/users")
public class UserController {
	
	@Autowired
	private IUser userRepositery;
	
	
	
	//ResponseEntity is meant to represent the entire HTTP response. 
	//You can control anything that goes into it: status code, headers, and body.
	
	
	@GetMapping("/")
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(userRepositery.findAll());
		
	}
	
	
	
	
	
	@GetMapping("/{idUser}")
    public ResponseEntity<?> findUserBysId(@PathVariable(name = "idUser") Long idUser) {
        if (idUser == null) {
            return ResponseEntity.badRequest().body("Cannot retrieve user with null ID");
        }
        User user = userRepositery.getOne(idUser);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(user);
    }
	
	

	// Creation d'un user
	
	@PostMapping("/")
	public ResponseEntity<?> createUser (@RequestBody User user){
		
		 if (user == null) {
	            return ResponseEntity.badRequest().body("Cannot create user with empty fields");
	        }
		 
	        User createdUser = userRepositery.save(user);
	        return ResponseEntity.ok(createdUser);
			
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login (@RequestParam(name="mail") String mail,@RequestParam(name="password") String password){
		if (StringUtils.isEmpty(mail) || StringUtils.isEmpty(password)) {
			
            return ResponseEntity.badRequest().body("Cannot login with empty user mail or password");
        }
		
		User authenticateUser = userRepositery.findByMailAndPassword(mail, password);
		if (authenticateUser == null) {
	            return ResponseEntity.notFound().build();
	        }
		return ResponseEntity.ok(authenticateUser);
		
	}
	
	

}


















package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.EditDTO;
import com.app.dto.LoginDTO;
import com.app.dto.UserDTO;
import com.app.entities.User;
import com.app.service.UserImpl;

@RestController
@RequestMapping("/")
@CrossOrigin
public class HomeController {

	
	@Autowired
	UserImpl userServ;
	
	@PostMapping("/signin")
	public ResponseEntity<?> signIn(@RequestBody LoginDTO loginDetails) {
		System.out.println("in signin :: " + loginDetails);
		User user = userServ.authenticateUser(loginDetails);
		if (user == null)
			return new ResponseEntity<>("User Not Found !!!!", HttpStatus.OK);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@PutMapping("/edit-profile/{userId}")
	public ResponseEntity<?> editProfile(@PathVariable int userId, @RequestBody EditDTO editDTO){
		   System.out.println("in editProfile: "+editDTO);
		   return ResponseEntity.ok(userServ.editProfile(userId,editDTO));
	}
	
	@PutMapping("/change_pass/{userId}/{pwd}")
	public ResponseEntity<?> changePassword(@PathVariable int userId,@PathVariable String pwd){
		System.out.println("userId = "+userId+" "+"password = "+pwd);
		return ResponseEntity.ok(userServ.changePassword(userId,pwd));
	}
	
	@PostMapping("/signup")
	public ResponseEntity<UserDTO> signUp(@RequestBody UserDTO userDto) {
		System.out.println("userDto = " + userDto);
		return new ResponseEntity<>(userServ.saveUser(userDto), HttpStatus.CREATED);
	}
	
}

package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApplyLoanDTO;
import com.app.dto.EditDTO;
import com.app.dto.HelpRequestDTO;
import com.app.dto.LoginDTO;
import com.app.dto.UserDTO;
import com.app.dto.UserFeedbackDTO;
import com.app.entities.User;
import com.app.service.UserImpl;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

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

	@PostMapping("/signup")
	public ResponseEntity<UserDTO> signUp(@RequestBody UserDTO userDto) {
		System.out.println("userDto = " + userDto);
		return new ResponseEntity<>(userServ.saveUser(userDto), HttpStatus.CREATED);
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
	
	@GetMapping("/user_loan_details/{userId}")
	public ResponseEntity<?>getUserLoan(@PathVariable int userId){
		System.out.println("userId = "+userId);
		return ResponseEntity.ok(userServ.getUserLoanDetails(userId));
	}
	
	@PostMapping("/apply_loan/{userId}")
	public ResponseEntity<?>applyLoan(@PathVariable int userId ,@RequestBody ApplyLoanDTO loanDto){
		
		System.out.println("loan Dto = "+loanDto+"userID = "+userId);
		return new ResponseEntity<>(userServ.saveLoanDetails(userId,loanDto), HttpStatus.CREATED);
		
	}
	
	@PostMapping("/help_request/{userId}")
	public ResponseEntity<?>helpRequest(@PathVariable int userId,@RequestBody HelpRequestDTO helpDto){
		System.out.println("helpDto = "+helpDto+" "+"userId = "+userId);
		return new ResponseEntity<>(userServ.helpRequest(userId,helpDto), HttpStatus.CREATED);
		
	}
	
	@GetMapping("/help")
	public ResponseEntity<?> helpList() {
		System.out.println("in help list");
		return ResponseEntity.ok(userServ.helpList());
	}
	
	@PutMapping("/addfeedback/{userId}/{feedbackId}")
	public ResponseEntity<?>addFeedBack(@PathVariable int userId,@PathVariable int feedbackId,@RequestBody UserFeedbackDTO userfeedback){
		System.out.println("userId = "+userId+"feedback id = "+feedbackId+"userfeedback = "+userfeedback);
		return ResponseEntity.ok(userServ.addFeedBack(userId,feedbackId,userfeedback));
	}
    
}

package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AddCommentDTO;
import com.app.dto.ApplyLoanDTO;
import com.app.dto.AssignFieldOfficerDTO;
import com.app.dto.AssignLoanVerificationDTO;
import com.app.dto.FeedbackDTO;
import com.app.entities.Feedback;
import com.app.service.AdminServiceImpl;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {

	@Autowired
	AdminServiceImpl adminServ;

	@GetMapping("/customerlist")
	public ResponseEntity<?> getCustomerList() {
		System.out.println("in getCustomerList: ");
		return ResponseEntity.ok(adminServ.getAllCustomers());
	}

	@GetMapping("/loanofficerlist")
	public ResponseEntity<?> getLoanOfficerList() {
		System.out.println("in getLoanOfficerList: ");
		return ResponseEntity.ok(adminServ.getAllLoanOfficer());
	}

	@GetMapping("/fieldofficerlist")
	public ResponseEntity<?> getFieldOfficerList() {
		System.out.println("in getFieldOfficerList: ");
		return ResponseEntity.ok(adminServ.getAllFieldOfficer());
	}

	@GetMapping("/loanrequestlist")
	public ResponseEntity<?> getLoanRequestList() {
		System.out.println("in getLoanRequestList: ");
		List<ApplyLoanDTO> list = adminServ.getAllLoanRequest();
		if (list.isEmpty())
			return new ResponseEntity<>("Empty List", HttpStatus.OK);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@PostMapping("/assignfieldofficer")
	public ResponseEntity<?> assignFieldOfficer(@RequestBody AssignFieldOfficerDTO fieldOfficer) {
		System.out.println("assignFieldOfficer = " + fieldOfficer);
		return ResponseEntity.ok(adminServ.assignFieldOfficer(fieldOfficer));
	}

	@GetMapping("/listbgvfo")
	public ResponseEntity<?> listOfBGVFO() {
		System.out.println("in list of Background verification officer ");
		List<Object> list = adminServ.listOfBGVFO();
		if (list.isEmpty())
			return new ResponseEntity<>("Empty List", HttpStatus.OK);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/updateBGverification")
	public ResponseEntity<?> updateBGVFO() {
		System.out.println("in update of Background verification officer ");
		return ResponseEntity.ok(adminServ.updateBGVFOList());
	}

	@PostMapping("/assignloanofficer")
	public ResponseEntity<?> assignLoanOfficer(@RequestBody AssignLoanVerificationDTO loanOfficer) {
		System.out.println("assignLoanOfficer = " + loanOfficer);
		return ResponseEntity.ok(adminServ.assignLoanOfficer(loanOfficer));
	}

	@GetMapping("/listlvfo")
	public ResponseEntity<?> listOfLVFO() {
		System.out.println("in list of Loan verification officer ");
		List<Object> list = adminServ.listOfLVFO();
		if (list.isEmpty())
			return new ResponseEntity<>("Empty List", HttpStatus.OK);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/updateloanverification")
	public ResponseEntity<?> updateLVFO() {
		System.out.println("in update of Loan verification officer ");
		return ResponseEntity.ok(adminServ.updateLVFOList());
	}

	@GetMapping("/helplist")
	public ResponseEntity<?> helpList() {
		System.out.println("in help list");
		return ResponseEntity.ok(adminServ.helpList());
	}

	@PostMapping("/addfeedback")
	public ResponseEntity<?> addFeedback(@RequestBody FeedbackDTO feedbackDto) {
		System.out.println("feedbackDto" + feedbackDto);
		return ResponseEntity.ok(adminServ.addFeedback(feedbackDto));
	}

	@GetMapping("/feedbackquestions")
	public ResponseEntity<?> getFeedbackQuestions() {
		System.out.println("in feedback question");

		List<Feedback> list = adminServ.getFeedbackQue();
		if (list.isEmpty())
			return new ResponseEntity<>("Empty List", HttpStatus.OK);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@PostMapping("/add_comment/{requestId}")
	public ResponseEntity<?> addComment(@PathVariable int requestId,@RequestBody AddCommentDTO comment){
		System.out.println("requestId = "+requestId+"comment = "+comment);
		//return ResponseEntity.ok(adminServ.addComment(requestId,comment));
		return new ResponseEntity<>(adminServ.addComment(requestId,comment), HttpStatus.CREATED);
	}
	
	@GetMapping("/userfeedback")
	public ResponseEntity<?> userFeedback() {
		System.out.println("in user feedback question");
		return ResponseEntity.ok(adminServ.userFeedback());
	}   
		

}

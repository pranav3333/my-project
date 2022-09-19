package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.UpdateBackgroundVerificationDTO;
import com.app.service.IFieldOfficer;

@RestController
@RequestMapping("/fieldofficer")
@CrossOrigin
public class FieldOfficerController {

	@Autowired
	IFieldOfficer officerDao;
	
	@GetMapping("/loan_requests/{fieldofficerId}")
	public ResponseEntity<?>getLoanRequests(@PathVariable int fieldofficerId){
		System.out.println("officerId = "+fieldofficerId);
		return ResponseEntity.ok(officerDao.getLoanRequests(fieldofficerId));
	}
	
	@GetMapping("/asignbvf/{fieldofficerId}")
	public ResponseEntity<?>asignBVf(@PathVariable int fieldofficerId){
		System.out.println("officerId = "+fieldofficerId);
		return ResponseEntity.ok(officerDao.getAssignedRequest(fieldofficerId));
	}
	
	@PostMapping("/updatebvf/{loanId}")
	public ResponseEntity<?>updateBVF(@PathVariable int loanId,@RequestBody UpdateBackgroundVerificationDTO updateDto){
		System.out.println("loanId = "+loanId+" "+"updateDto =  "+updateDto);
		return ResponseEntity.ok(officerDao.updateBvf(loanId,updateDto));
	}
	
}

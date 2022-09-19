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

import com.app.dto.UpdateLoanVerificationDTO;
import com.app.service.ILoanOfficer;

@RestController
@RequestMapping("/loanofficer")
@CrossOrigin
public class LoanOfficerController {
	
@Autowired
ILoanOfficer officerDao;

	@GetMapping("/loan_requests/{loanofficerId}")
	public ResponseEntity<?>getLoanRequests(@PathVariable int loanofficerId){
		System.out.println("officerId = "+loanofficerId);
		return ResponseEntity.ok(officerDao.getLoanRequests(loanofficerId));
	}
	
	@GetMapping("/asignlvf/{loanofficerId}")
	public ResponseEntity<?>asignLVf(@PathVariable int loanofficerId){
		System.out.println("officerId = "+loanofficerId);
		return ResponseEntity.ok(officerDao.getAssignedRequest(loanofficerId));
	}
	
	@PostMapping("/updatelvf/{loanId}")
	public ResponseEntity<?>updateLvf(@PathVariable int loanId,@RequestBody UpdateLoanVerificationDTO updateDto){
		System.out.println("loanId = "+loanId+" "+"updateDto =  "+updateDto);
		return ResponseEntity.ok(officerDao.updateLvf(loanId,updateDto));
	}
	

	
}

package com.app.service;

import java.util.List;

import com.app.dto.AddCommentDTO;
import com.app.dto.ApplyLoanDTO;
import com.app.dto.AssignFieldOfficerDTO;
import com.app.dto.AssignLoanVerificationDTO;
import com.app.dto.FeedbackDTO;
import com.app.dto.HelpDTO;
import com.app.entities.Feedback;
import com.app.entities.User;

public interface IAdminService {

	List<User> getAllCustomers();
	
	List<User> getAllLoanOfficer();
	
	List<User> getAllFieldOfficer();
	
	List<ApplyLoanDTO>getAllLoanRequest();

	String assignLoanOfficer(AssignLoanVerificationDTO loanOfficer);
	
	List<Object> listOfBGVFO();
	
	List<Object> updateBGVFOList();

	String assignFieldOfficer(AssignFieldOfficerDTO fieldOfficer);

	List<Object> listOfLVFO();

	List<Object> updateLVFOList();

	List<HelpDTO> helpList();

	FeedbackDTO addFeedback(FeedbackDTO feedbackDto);

	List<Feedback> getFeedbackQue();

	List<Object> userFeedback();

	AddCommentDTO addComment(int requestId, AddCommentDTO comment);

	//List<AdminUserFeedbackDTO> userFeedback();
}

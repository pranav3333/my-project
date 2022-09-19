package com.app.service;

import java.util.List;

import com.app.dto.ApplyLoanDTO;
import com.app.dto.EditDTO;
import com.app.dto.HelpDTO;
import com.app.dto.HelpRequestDTO;
import com.app.dto.LoginDTO;
import com.app.dto.UserDTO;
import com.app.dto.UserFeedbackDTO;
import com.app.entities.User;

public interface IUser {

	User authenticateUser(LoginDTO loginDetails);

	UserDTO saveUser(UserDTO userDto);
	
	UserDTO editProfile(int userId,EditDTO editDTO);
	
	String changePassword(int userId, String pwd);

	List<ApplyLoanDTO> getUserLoanDetails(int userId);

	ApplyLoanDTO saveLoanDetails(int userId,ApplyLoanDTO loanDto);

	String addFeedBack(int userId,int feedbackId,UserFeedbackDTO userfeedback);

	List<HelpDTO> helpList();

	HelpRequestDTO helpRequest(int userId,HelpRequestDTO helpDto);
}

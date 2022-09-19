package com.app.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.ApplyLoanRepository;
import com.app.dao.FeedbackRepository;
import com.app.dao.HelpRepository;
import com.app.dao.UserFeedbackRepository;
import com.app.dao.UserRepository;
import com.app.dto.ApplyLoanDTO;
import com.app.dto.EditDTO;
import com.app.dto.HelpDTO;
import com.app.dto.HelpRequestDTO;
import com.app.dto.LoginDTO;
import com.app.dto.UserDTO;
import com.app.dto.UserFeedbackDTO;
import com.app.entities.ApplyLoan;
import com.app.entities.Feedback;
import com.app.entities.Help;
import com.app.entities.User;
import com.app.entities.UserFeedback;

@Service
@Transactional
public class UserImpl implements IUser {

	@Autowired
	UserRepository userDao;

	@Autowired
	ApplyLoanRepository loanDao;
	
	@Autowired
	FeedbackRepository feedbackDao;
	
	@Autowired
	UserFeedbackRepository userfeedbackDao;
	
	@Autowired
	HelpRepository helpDao;

	@Autowired
	ModelMapper mapper;

	@Override
	public User authenticateUser(LoginDTO loginDetails) {

		return userDao.authenticateUser(loginDetails.getEmail(), loginDetails.getPassword());

	}

	@Override
	public UserDTO saveUser(UserDTO userDto) {
		User user = mapper.map(userDto, User.class);
		System.out.println("user : " + user);
		user = userDao.save(user);
		return mapper.map(user, UserDTO.class);
	}

	@Override
	public UserDTO editProfile(int userId, EditDTO editDTO) {
		User user = userDao.findById(userId).get();
		System.out.println("earlier user :: " + user);

		user.setFirst_name(editDTO.getFirst_name());
		user.setLast_name(editDTO.getLast_name());
		user.setContact(editDTO.getContact());
		user.setEmail(editDTO.getEmail());

		System.out.println("user = " + user.getFirst_name() + "  " + user.getLast_name());
		return mapper.map(user, UserDTO.class);
	}

	@Override
	public String changePassword(int userId, String pwd) {
		User u = userDao.findById(userId).get();
		u.setPassword(pwd);
		return "Password Changed Successfully";
	}

	@Override
	public List<ApplyLoanDTO> getUserLoanDetails(int userId) {
		User u = userDao.findById(userId).get();
		List<ApplyLoan> loan = loanDao.findUserLoanDetails(u);
		List<ApplyLoanDTO> temp = new ArrayList<>();
		loan.forEach(e -> temp.add(mapper.map(e, ApplyLoanDTO.class)));
		return temp;
	}

	@Override
	public ApplyLoanDTO saveLoanDetails(int userId,ApplyLoanDTO loanDto) {
		ApplyLoan loan = mapper.map(loanDto,ApplyLoan.class);
		User u = userDao.findById(userId).get();
		loan.setUser(u);
		System.out.println("loan = "+loan);
		loan = loanDao.save(loan);
		return mapper.map(loan,ApplyLoanDTO.class);
	}

	@Override
	public String addFeedBack(int userId,int feedbackId,UserFeedbackDTO userfeedback) {
		
		User u = userDao.findById(userId).get();
		Feedback feedback=feedbackDao.findById(feedbackId).get();
		UserFeedback uf = new UserFeedback();
		uf.setFeedback(feedback);
		uf.setUser(u);
		uf.setUser_feedback(userfeedback.getUser_feedback());

		uf=userfeedbackDao.save(uf);
		System.out.println("user feed back = "+uf);
		
		return "User Feedback Added Successfully";
	}

	@Override
	public List<HelpDTO> helpList() {

		List<Help> list = helpDao.findAll();

		List<HelpDTO> temp = new ArrayList<>();

		list.forEach(e -> temp.add(mapper.map(e,HelpDTO.class)));

		return temp;
	}

	@Override
	public HelpRequestDTO helpRequest(int userId,HelpRequestDTO helpDto) {
		Help h =mapper.map(helpDto,Help.class);
		User user = userDao.findById(userId).get();
		h.setUser(user);
		h=helpDao.save(h);
		return mapper.map(h,HelpRequestDTO.class);
	}

}

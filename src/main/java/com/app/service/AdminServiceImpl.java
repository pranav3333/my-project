package com.app.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.ApplyLoanRepository;
import com.app.dao.AssignFieldOfficerRepository;
import com.app.dao.AssignLoanOfficerRepository;
import com.app.dao.FeedbackRepository;
import com.app.dao.HelpRepository;
import com.app.dao.UserFeedbackRepository;
import com.app.dao.UserRepository;
import com.app.dto.AddCommentDTO;
import com.app.dto.ApplyLoanDTO;
import com.app.dto.AssignFieldOfficerDTO;
import com.app.dto.AssignLoanVerificationDTO;
import com.app.dto.FeedbackDTO;
import com.app.dto.HelpDTO;
import com.app.entities.ApplyLoan;
import com.app.entities.AssignBackgroundVerification;
import com.app.entities.AssignLoanVerification;
import com.app.entities.Feedback;
import com.app.entities.Help;
import com.app.entities.User;

@Service
@Transactional
public class AdminServiceImpl implements IAdminService {

	@Autowired
	UserRepository userDao;

	@Autowired
	ApplyLoanRepository loanDao;

	@Autowired
	AssignLoanOfficerRepository loanOfficerDao;

	@Autowired
	AssignFieldOfficerRepository fieldOfficerDao;

	@Autowired
	HelpRepository helpDao;
	
	@Autowired
	FeedbackRepository feedbackDao;
	
	@Autowired
	UserFeedbackRepository userFeedbackDao;

	@Autowired
	ModelMapper mapper;

	@Override
	public List<User> getAllCustomers() {
		return userDao.findByUser_Category("User");
	}

	@Override
	public List<User> getAllLoanOfficer() {

		return userDao.findByUser_Category("loanofficer");
	}

	@Override
	public List<User> getAllFieldOfficer() {

		return userDao.findByUser_Category("fieldofficer");
	}

	@Override
	public List<ApplyLoanDTO> getAllLoanRequest() {
		List<ApplyLoan> loan = loanDao.findAll();
		List<ApplyLoanDTO> temp = new ArrayList<>();
		loan.forEach(e -> temp.add(mapper.map(e, ApplyLoanDTO.class)));
		return temp;
	}

	@Override
	public String assignLoanOfficer(AssignLoanVerificationDTO loanOfficer) {

		ApplyLoan loan = loanDao.findById(loanOfficer.getLoan_id()).get();
		AssignLoanVerification loanOfficer1 = loanOfficerDao.findByName(loanOfficer.getLoan_officer_name());
		loan.setLoanOfficer(loanOfficer1);

		return "Loan Officer Assigned Successfully";
	}

	@Override
	public List<Object> listOfBGVFO() {

		return fieldOfficerDao.listOfBGVFO();
	}

	@Override
	public List<Object> updateBGVFOList() {

		return fieldOfficerDao.updateBGVFOList();
	}

	@Override
	public String assignFieldOfficer(AssignFieldOfficerDTO fieldOfficer) {

		ApplyLoan loan = loanDao.findById(fieldOfficer.getLoan_id()).get();
		AssignBackgroundVerification fieldOfficer1 = fieldOfficerDao.findByName(fieldOfficer.getField_officer_name());
		loan.setFieldOfficer(fieldOfficer1);

		return "Field Officer Assigned Successfully";
	}

	@Override
	public List<Object> listOfLVFO() {

		return loanOfficerDao.listOfLVFO();
	}

	@Override
	public List<Object> updateLVFOList() {

		return loanOfficerDao.updateLVFOList();
	}

	@Override
	public List<HelpDTO> helpList() {

		List<Help> list = helpDao.findAll();

		List<HelpDTO> temp = new ArrayList<>();

		list.forEach(e -> temp.add(mapper.map(e,HelpDTO.class)));

		return temp;
	}

	@Override
	public FeedbackDTO addFeedback(FeedbackDTO feedbackDto) {
		Feedback feedback = mapper.map(feedbackDto,Feedback.class);
		System.out.println("feedback = "+feedback);
		feedback = feedbackDao.save(feedback);
		return mapper.map(feedback,FeedbackDTO.class);
	}

	@Override
	public List<Feedback> getFeedbackQue() {
		return feedbackDao.findAll();
	}
	
	
	@Override
	public List<Object> userFeedback(){
		List<Object> list = userFeedbackDao.findUserFeedback();
		return list;
	}

	@Override
	public AddCommentDTO addComment(int requestId, AddCommentDTO comment) {
		Help help = helpDao.findById(requestId).get();
		help.setComment(comment.getComment());
		help=helpDao.save(help);
		return mapper.map(help,AddCommentDTO.class);
	}
	
/*
	@Override
	public List<AdminUserFeedbackDTO> userFeedback() {
		
		List<Feedback> feedback=feedbackDao.findAll();
		List<UserFeedback> UF =userFeedbackDao.findAll();
		
		List<AdminUserFeedbackDTO> temp = new ArrayList<>();
		UF.forEach(e -> temp.add(mapper.map(e,AdminUserFeedbackDTO.class)));
		feedback.forEach(e -> temp.add(mapper.map(e, AdminUserFeedbackDTO.class)));
		
		
		return temp;
	}
*/
	
}

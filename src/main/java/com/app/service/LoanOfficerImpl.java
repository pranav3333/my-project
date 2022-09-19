package com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.ApplyLoanRepository;
import com.app.dao.AssignFieldOfficerRepository;
import com.app.dao.AssignLoanOfficerRepository;
import com.app.dao.UpdateLoanVerificationRepo;
import com.app.dao.UserRepository;
import com.app.dto.ApplyLoanDTO;
import com.app.dto.AssignedLoanListDTO;
import com.app.dto.UpdateLoanVerificationDTO;
import com.app.entities.ApplyLoan;
import com.app.entities.AssignLoanVerification;
import com.app.entities.UpdateLoanVerification;

@Service
@Transactional
public class LoanOfficerImpl implements ILoanOfficer {

	@Autowired
	UserRepository userDao;

	@Autowired
	ApplyLoanRepository loanDao;

	@Autowired
	AssignLoanOfficerRepository loanOfficerDao;

	@Autowired
	AssignFieldOfficerRepository fieldOfficerDao;
	
	@Autowired
	UpdateLoanVerificationRepo updateLoanDao;

	@Autowired
	ModelMapper mapper;

	@Override
	public List<ApplyLoanDTO> getLoanRequests(int loanofficerId) {
		AssignLoanVerification loanOfficer = loanOfficerDao.findById(loanofficerId).get();
		List<ApplyLoan> loan = loanDao.findLoanOfficerDetails(loanOfficer);
		List<ApplyLoanDTO> temp = new ArrayList<>();
		loan.forEach(e -> temp.add(mapper.map(e, ApplyLoanDTO.class)));
		return temp;
	}

	@Override
	public List<AssignedLoanListDTO> getAssignedRequest(int loanofficerId) {
		AssignLoanVerification loanOfficer = loanOfficerDao.findById(loanofficerId).get();
		List<AssignedLoanListDTO> loan = loanDao.findLoanOfficerDetails(loanOfficer).stream().map(i->new AssignedLoanListDTO(i.getLoan_id(),i.getName())).collect(Collectors.toCollection(ArrayList::new));
		return loan;
	}

	@Override
	public UpdateLoanVerificationDTO updateLvf(int loanId,UpdateLoanVerificationDTO updateDto) {
		ApplyLoan loan = loanDao.findById(loanId).get();
		AssignLoanVerification loanOfficer = loanOfficerDao.findById(loan.getLoanOfficer().getLoan_officer_id()).get();
		UpdateLoanVerification updateLoan = mapper.map(updateDto,UpdateLoanVerification.class);	
		updateLoan.setLoanOfficer(loanOfficer);
		updateLoan.setLoan(loan);
		System.out.println("updateLoan = "+updateLoan);
		updateLoan = updateLoanDao.save(updateLoan);
		return mapper.map(updateLoan,UpdateLoanVerificationDTO.class);
	}

}

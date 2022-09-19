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
import com.app.dao.UpdateBackgroundVerificationRepo;
import com.app.dao.UserRepository;
import com.app.dto.ApplyLoanDTO;
import com.app.dto.AssignedBackgroundListDTO;
import com.app.dto.UpdateBackgroundVerificationDTO;
import com.app.entities.ApplyLoan;
import com.app.entities.AssignBackgroundVerification;
import com.app.entities.UpdateBackgroundVerification;

@Service
@Transactional
public class FieldOfficerImpl implements IFieldOfficer {

	@Autowired
	UserRepository userDao;

	@Autowired
	ApplyLoanRepository loanDao;

	@Autowired
	AssignLoanOfficerRepository loanOfficerDao;

	@Autowired
	AssignFieldOfficerRepository fieldOfficerDao;

   @Autowired
   UpdateBackgroundVerificationRepo updateFieldDao;

	@Autowired
	ModelMapper mapper;
	
	@Override
	public List<ApplyLoanDTO> getLoanRequests(int fieldofficerId) {
		AssignBackgroundVerification fieldOfficer = fieldOfficerDao.findById(fieldofficerId).get();
		List<ApplyLoan> loan = loanDao.findFieldOfficerDetails(fieldOfficer);
		List<ApplyLoanDTO> temp = new ArrayList<>();
		loan.forEach(e -> temp.add(mapper.map(e, ApplyLoanDTO.class)));
		return temp;
	}

	@Override
	public List<AssignedBackgroundListDTO> getAssignedRequest(int fieldofficerId) {
		AssignBackgroundVerification fieldOfficer = fieldOfficerDao.findById(fieldofficerId).get();
		List<AssignedBackgroundListDTO> loan = loanDao.findFieldOfficerDetails(fieldOfficer).stream().map(i->new AssignedBackgroundListDTO(i.getLoan_id(),i.getName())).collect(Collectors.toCollection(ArrayList::new));
		return loan;
	}

	@Override
	public UpdateBackgroundVerificationDTO updateBvf(int loanId,UpdateBackgroundVerificationDTO updateDto) {
		ApplyLoan loan = loanDao.findById(loanId).get();
		AssignBackgroundVerification fieldOfficer = fieldOfficerDao.findById(loan.getFieldOfficer().getField_officer_id()).get();
		UpdateBackgroundVerification updateBackground = mapper.map(updateDto,UpdateBackgroundVerification.class);	
		updateBackground.setFieldOfficer(fieldOfficer);
		updateBackground.setLoan(loan);
		System.out.println("updateBackground = "+updateBackground);
	    updateBackground = updateFieldDao.save(updateBackground);
		return mapper.map(updateBackground,UpdateBackgroundVerificationDTO.class);
	}

	
	
}

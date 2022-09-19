 package com.app.service;

import java.util.List;

import com.app.dto.ApplyLoanDTO;
import com.app.dto.AssignedLoanListDTO;
import com.app.dto.UpdateLoanVerificationDTO;

public interface ILoanOfficer {

	List<ApplyLoanDTO> getLoanRequests(int loanofficerId);

	List<AssignedLoanListDTO> getAssignedRequest(int loanofficerId);

	UpdateLoanVerificationDTO updateLvf(int loanId,UpdateLoanVerificationDTO updateDto);

}

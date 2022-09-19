package com.app.service;

import java.util.List;

import com.app.dto.ApplyLoanDTO;
import com.app.dto.AssignedBackgroundListDTO;
import com.app.dto.UpdateBackgroundVerificationDTO;

public interface IFieldOfficer {

	List<ApplyLoanDTO> getLoanRequests(int fieldofficerId);

	List<AssignedBackgroundListDTO> getAssignedRequest(int fieldofficerId);

	UpdateBackgroundVerificationDTO updateBvf(int loanId,UpdateBackgroundVerificationDTO updateDto);

}

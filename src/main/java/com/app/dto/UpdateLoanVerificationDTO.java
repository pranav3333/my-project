package com.app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter

@Setter

@ToString

public class UpdateLoanVerificationDTO {
	private Integer update_loan_id;
	private String verification_update;
	private String description;
	private String status;
	private String report;
}

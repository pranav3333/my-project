package com.app.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ApplyLoanDTO {
	
	private Integer loan_id;
	
	private String name;
	@Range(min = 0, max = 9, message = "Invalid Contact")
	private long phone;
	@NotBlank
	@Email(message = "Invalid Email")
	private String email;
	@NotBlank
	private String loan_type;
	@NotBlank
	private String resume;
	@NotBlank
	private String project_plan;
	@NotBlank
	private String personal_credit_report;
	@NotBlank
	private String business_credit_report;
	@NotBlank
	private String financial_statement;
	@NotBlank
	private String bank_statement;
	@NotBlank
	private int loan_amount;
	@NotBlank
	private int loan_tenure;
	@NotBlank
	private String EMI_option;
	@NotBlank
	private String address;
	@NotBlank
	private String status;
}

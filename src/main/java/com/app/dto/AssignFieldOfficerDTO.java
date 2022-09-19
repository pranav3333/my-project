package com.app.dto;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class AssignFieldOfficerDTO {

	private int loan_id;
	@Length(min = 4, max = 30, message = "Invalid name length")
	private String field_officer_name;
}

package com.app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter

@Setter

@ToString

public class UpdateBackgroundVerificationDTO {
	private Integer update_background_id;
	private String user_name;
	private String verification_update;
	private String description;
	private String report;
}

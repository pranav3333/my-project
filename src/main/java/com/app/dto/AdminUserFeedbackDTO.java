package com.app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AdminUserFeedbackDTO {
	private String feedback_question;
	private String user_feedback;
}

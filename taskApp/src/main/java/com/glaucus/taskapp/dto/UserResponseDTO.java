package com.glaucus.taskapp.dto;

import com.glaucus.taskapp.model.User;

import lombok.Getter;
import lombok.Setter;

public class UserResponseDTO {
	
	@Setter
	@Getter
	private User user;
	
	@Setter
	@Getter
	private String message;
	
}

package com.glaucus.taskapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.glaucus.taskapp.dto.UserResponseDTO;
import com.glaucus.taskapp.model.User;
import com.glaucus.taskapp.repository.UserRepository;
import com.glaucus.taskapp.repository.UserRepositoryImpl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping(value = "/user")
@Log4j
@NoArgsConstructor
@AllArgsConstructor
public class UserController {

	//@Autowired
	@Getter
	@Setter
	private UserRepository userRepository;

	@RequestMapping(value = "/updateCount", method = RequestMethod.PUT)
	public @ResponseBody UserResponseDTO updateCount(@RequestParam("id") int id) {

		UserResponseDTO userResponseDTO = new UserResponseDTO();

		User theUser = userRepository.updateUserCount(id);

		userResponseDTO.setUser(theUser);

		if (theUser == null) {
			userResponseDTO.setMessage("the user doesnt exists");
		} else {
			userResponseDTO.setMessage("count has been incremented by 1");
		}

		return userResponseDTO;
	}

	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	public @ResponseBody UserResponseDTO getUser(@RequestParam("id") int id) {

		UserResponseDTO userResponseDTO = new UserResponseDTO();

		User theUser = userRepository.getUser(id);

		userResponseDTO.setUser(theUser);

		if (theUser == null) {
			userResponseDTO.setMessage("user not found");
		} else {
			userResponseDTO.setMessage("user found");
		}

		return userResponseDTO;
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public @ResponseBody UserResponseDTO addUser(@RequestParam("name") String name) {

		UserResponseDTO userResponseDTO = new UserResponseDTO();

		User theUser = userRepository.addUser(name);

		userResponseDTO.setUser(theUser);

		if (theUser == null) {
			userResponseDTO.setMessage("some error occured, please try again later");
		} else {
			userResponseDTO.setMessage("user created successfully");
		}

		return userResponseDTO;
	}

}

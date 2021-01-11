package com.glaucus.taskapp.repository;

import com.glaucus.taskapp.model.User;

public interface UserRepository {

	User updateUserCount(int id);
	User getUser(int id);
	User addUser(String name);
	
}

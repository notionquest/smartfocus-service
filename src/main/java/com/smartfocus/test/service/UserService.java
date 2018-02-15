package com.smartfocus.test.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.smartfocus.test.db.DB;
import com.smartfocus.test.model.User;

public class UserService {

	private Map<Long, User> usersDb = DB.getUsersFromDb();

	public UserService() {
		usersDb.put(1L, new User(1, "Test1"));
		usersDb.put(2L, new User(2, "Test 2"));
	}

	public List<User> getUsersDb() {
		List<User> users = new ArrayList<User>(usersDb.values());
		return users;
	}

}

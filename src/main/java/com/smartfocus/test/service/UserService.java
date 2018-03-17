package com.smartfocus.test.service;

import com.smartfocus.test.db.DB;
import com.smartfocus.test.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service("userService")
public class UserService {

	private Logger LOG = Logger.getLogger(getClass().getName());

	private Map<Long, User> usersDb = DB.getUsersFromDb();

	public UserService() {
		usersDb.put(1L, new User(1, "Test1"));
		usersDb.put(2L, new User(2, "Test 2"));
	}

	public List<User> getUsersDb() {
		List<User> users = new ArrayList<>(usersDb.values());
		//List<User> users = usersDb.entrySet().stream().map(e-> e.getValue()).collect(Collectors.toList());
		LOG.info(users.stream().map(e->e.getName()).collect(Collectors.joining("")));
		return users;
	}

}

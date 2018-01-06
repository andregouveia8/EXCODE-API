package com.excode.imp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.excode.model.User;

public class UserManager {

	static List<User> users = new ArrayList<User>();
	static UserManager userManager = null;

	public static UserManager getInstance() {

		if (userManager == null) {
			userManager = new UserManager();

			User user1 = new User("andregouveia8", "porto", "asd@xpto.com", "Andr� Gouveia");
			User user2 = new User("andregouveia9", "porto", "asd@xpto.com", "Andr� Gouveia");
			User user3 = new User("paulo", "porto", "asd@xpto.com", "Andr� Gouveia");

			users.add(user1);
			users.add(user2);
			users.add(user3);

		}
		return userManager;
	}

	public List<User> getUsers() {
		return users;
	}

	public User getUser(String userID) {
		for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
			User user = (User) iterator.next();
			if (user.getUserID().equals(userID)) {
				return user;
			}
		}
		return null;
	}

	public void removeUser(String userID) {
		for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
			User user = (User) iterator.next();
			if (user.getUserID().equals(userID))
				iterator.remove();
		}
	}

	public void createUser(String userID, String email, String password, String nameUser) {

		User user = new User(userID, email, password, nameUser);
		users.add(user);

	}

}
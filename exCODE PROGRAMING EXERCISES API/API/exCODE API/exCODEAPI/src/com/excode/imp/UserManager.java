package com.excode.imp;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import com.excode.data.UserData;
import com.excode.model.User;

import io.jsonwebtoken.impl.crypto.MacProvider;

public class UserManager implements IUser {

	static List<User> users = new ArrayList<User>();
	static Key key;

	static UserManager userManager = null;

	public static UserManager getInstance() {
		if (userManager == null) {
			userManager = new UserManager();
			key = MacProvider.generateKey();
		}
		return userManager;
	}

	// GET ALL USERS
	public List<User> getUsers() {
		UserData userData = UserData.getInstance();
		return userData.getUsers();
	}

	// GET A USER = USERID
	public List<User> getUser(String userID) {
		UserData userData = UserData.getInstance();
		return userData.getUser(userID);
	}

	// DELETE A USER = USERID
	public Response deleteUser(String userID, String password, String user_ID) {

		List<User> user = UserData.getInstance().getUser(userID);
		String userPassword = null;
		for (User user_Temp : user) {
			userPassword = user_Temp.getPassword();
		}
		// CHECKS IF THE USER LOGGED IS THE AUTHOR OF THE EXERCISE
		if (userID.equals(user_ID) && (password.equals(userPassword))) {

			return UserData.getInstance().removeUser(userID, password, user_ID);
		} else {
			System.out.println("VOCE NAO PODE REMOVER ESTE USER PQ NAO TEM PERMISSOES");
		}
		return null;

	}
	
	//CHANGE PASSWORD USERID
	public Response changePassword(String userID, String password, String newPassword, String user_ID) {
		
		List<User> user = UserData.getInstance().getUser(userID);
		
		String userPassword = null;
		for (User user_Temp : user) {
			userPassword = user_Temp.getPassword();
		}
		
		
		
		// CHECKS IF THE USER LOGGED == USER TO UPDATE
		if (userID.equals(user_ID) && (password.equals(userPassword))) {
			UserData.getInstance().updatePassword(userID, newPassword);
			
		} else {
			System.out.println("VOCE NAO PODE ALTERAR A PASS PQ NAO TEM PERMISSOES");
		}
		return null;
	}

	// POST USER
	public void createUser(String userID, String email, String password, String nameUser) {

		UserData userData = UserData.getInstance();
		userData.insertUser(new User(userID,  password, email,nameUser));

	}

	//GET KEY TOKEN
	public Key getKey() {
		return key;
	}

	//CHECK CREDENTIALS AUTHENTICATION
	public boolean checkCredentials(String userID, String password) {
		UserData userData = UserData.getInstance();
		return userData.checkCredentials(userID, password);

	}

	

}

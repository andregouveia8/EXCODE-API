package com.excode.data;

import static com.mongodb.client.model.Filters.*;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.excode.model.Exercise;
import com.excode.model.User;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;

public class UserData {

	static UserData userData = null;
	static MongoCollection<User> colUsers;

	ExerciseData gameData = ExerciseData.getInstance();

	public static UserData getInstance() {
		if (userData == null) {
			userData = new UserData();

			CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
					fromProviders(PojoCodecProvider.builder().automatic(true).build()));
			MongoClient mongoClient = new MongoClient("localhost",
					MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
			MongoDatabase dbUser = mongoClient.getDatabase("db");
			colUsers = dbUser.getCollection("User", User.class);
		}
		return userData;
	}

	// GET ALL USERS
	public List<User> getUsers() {
		final List<User> users = new ArrayList<User>();
		Block<User> printBlock = new Block<User>() {
			public void apply(final User user) {
				users.add(user);
			}
		};
		colUsers.find().forEach(printBlock);
		return users;
	}

	// GET A SPECIFIC USER
	public List<User> getUser(String userID) {
		final List<User> users = new ArrayList<User>();
		Block<User> printBlock = new Block<User>() {
			public void apply(final User user) {
				users.add(user);
			}
		};
		colUsers.find(eq("userID", userID)).forEach(printBlock);

		return users;
	}

	// DELETE USER
	public Response removeUser(String userID, String password, String user_ID) {
		colUsers.deleteOne(eq("userID", userID));
		return null;
	}

	// POST NEW USER
	public Response insertUser(User user) {
		if (colUsers.find(eq("userID", user.getUserID())).first() == null) {
			colUsers.insertOne(user);
		}
		return Response.serverError().status(200).type("text/plain").entity("User Already Exists").build();

	}

	// FUNCTION CHECK CREDENTIALS LOGIN
	public boolean checkCredentials(String userID, String password) {
		if (colUsers.find(and(eq("userID", userID), eq("password", password))).first() == null) {
			return false;
		} else {
			return true;
		}
	}

	// UPDATE PASSWORD
	public Response updatePassword(String user_ID, String newPassword) {

		Document setPassword = new Document();
		setPassword.append("password", newPassword);
		Document update = new Document();
		update.append("$set", setPassword);

		colUsers.updateOne(eq("userID", user_ID), update);

		return null;

	}

}

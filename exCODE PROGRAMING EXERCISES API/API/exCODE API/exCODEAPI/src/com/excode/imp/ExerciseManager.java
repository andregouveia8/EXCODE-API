package com.excode.imp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import com.excode.data.ExerciseData;
import com.excode.data.UserData;
import com.excode.model.Exercise;
import com.excode.model.Solution;
import com.excode.model.TestCase;
import com.excode.model.User;

public class ExerciseManager implements IExercise {

	static ExerciseManager exerciseManager = null;



	public static ExerciseManager getInstance() {
		if (exerciseManager == null) {
			exerciseManager = new ExerciseManager();

		}
		return exerciseManager;
	}

	// GET ALL EXERCISES
	public List<Exercise> getExercises() {
		ExerciseData exerciseData = ExerciseData.getInstance();
		return exerciseData.getExercises();
	}

	// GET A SPECIFIC EXERCISE by EXERCISEID
	public List<Exercise> getExercise_ID(String exerciseID) {
		ExerciseData exerciseData = ExerciseData.getInstance();
		return exerciseData.getExercises_ID(exerciseID);
	}

	// GET A SPECIFIC EXERCISE by DIFCULTY
	public List<Exercise> getExercisesDificulty(String dificulty) {
		ExerciseData exerciseData = ExerciseData.getInstance();
		return exerciseData.getExercisesDificulty(dificulty);
	}

	// GET A SPECIFIC EXERCISE by DURATION
	public List<Exercise> getExercisesDuration(int duration) {
		ExerciseData exerciseData = ExerciseData.getInstance();
		return exerciseData.getExercisesDuration(duration);
	}

	// GET A SPECIFIC EXERCISE by LANGUAGE
	public List<Exercise> getExercisesLanguage(String language) {
		ExerciseData exerciseData = ExerciseData.getInstance();
		return exerciseData.getExercisesLanguage(language);
	}

	// GET A SPECIFIC EXERCISE by TAG
	public List<Exercise> getExercisesTag(String tag) {
		ExerciseData exerciseData = ExerciseData.getInstance();
		return exerciseData.getExercisesTag(tag);
	}

	// GET A SPECIFIC EXERCISE by USER
	public List<Exercise> getExercisesUser(String userID) {
		ExerciseData exerciseData = ExerciseData.getInstance();
		return exerciseData.getExercisesUser(userID);
	}

	// DELETE A SPECIFIC EXERCISE
	public Response removeExercise(String exerciseID, String userIDAuthor) {

		List<Exercise> exercise = ExerciseData.getInstance().getExercises_ID(exerciseID);
		String userEx = null;
		for (Exercise ex : exercise) {
			userEx = ex.author.getUserID();
		}
		// CHECKS IF THE USER LOGGED IS THE AUTHOR OF THE EXERCISE
		if (userIDAuthor.equals(userEx)) {
			ExerciseData exerciseData = ExerciseData.getInstance();
			System.out.println("REMOVIDO COM SUCESSO");
			return exerciseData.removeExercise(exerciseID);
			
		} else {
			System.out.println("VOCE NAO PODE REMOVER ESTE EXERCISE PQ NAO TEM PERMISSOES");
		}
		return null;
	}

	// CREATE A NEW EXERCISE
	public void createExercise(String exerciseID, String title, String userIDAuthour, String statement,
			String dificulty, int duration, String tags, String code, String language, String input, String output) {

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();

		String creationDate = dateFormat.format(date);
		System.out.println(dateFormat.format(date)); // 2016/11/16 12:08:43

		List<User> users = UserManager.getInstance().getUser(userIDAuthour);

		String userID = null;
		String password = null;
		String name = null;
		String email = null;

		for (User user : users) {
			userID = user.getUserID();
			name = user.getName();
			email = user.getEmail();
		}
		User userCreator = new User(userID, email, name);
		Solution solution = new Solution(code, language);
		TestCase testCase = new TestCase(input, output);

		ExerciseData exerciseData = ExerciseData.getInstance();
		exerciseData.createExercise(new Exercise(exerciseID, creationDate, title, userCreator, statement, dificulty,
				duration, tags, solution, testCase));

	}

}

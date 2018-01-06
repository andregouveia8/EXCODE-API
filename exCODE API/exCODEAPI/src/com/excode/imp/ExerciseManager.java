package com.excode.imp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.excode.model.Exercise;
import com.excode.model.Solution;
import com.excode.model.User;

public class ExerciseManager {

	static List<Exercise> exercises = new ArrayList<Exercise>();
	static List<Solution> solutions = new ArrayList<Solution>();
	static ExerciseManager exerciseManager = null;

	public static ExerciseManager getInstance() {
		if (exerciseManager == null) {
			exerciseManager = new ExerciseManager();

			User user = new User("andregouveia8", "porto", "asd@xpto.com", "Andr� Gouveia");
			User user1 = new User("andregouveia9", "porto", "asd@xpto.com", "Andr� Gouveia");
			Solution sol = new Solution("HTML", "Java");
			Solution sol1 = new Solution("HTML CODE", "C#");
			solutions.add(sol);
			solutions.add(sol1);
			Exercise exercise1 = new Exercise(1, "18-10-2017 19:30", "Factorial", user, "Crie um algoritmo ...",
					"Pro", 13, null, solutions, null);
			Exercise exercise2 = new Exercise(2, "18-10-2017 19:30", "Meteo", user1, "Crie um algoritmo ...",
					"Expert", 3, null, null, null);
			Exercise exercise3 = new Exercise(2, "18-10-2017 19:30", "Post", user1, "Crie um algoritmo ...", "Pro",
					13, null, null, null);

			exercises.add(exercise1);
			exercises.add(exercise2);
			exercises.add(exercise3);

		}
		return exerciseManager;
	}

	public List<Exercise> getExercises() {
		return exercises;
	}

	// GET A EXERCISE ID = EXERCISEID
	public Exercise getExercise(int exerciseID) {
		for (Iterator<Exercise> iterator = exercises.iterator(); iterator.hasNext();) {
			Exercise exercise = (Exercise) iterator.next();
			if (exercise.getExerciseID() == exerciseID) {
				return exercise;
			}
		}
		return null;

	}

	public void removeExercise(int exerciseID) {
		for (Iterator<Exercise> iterator = exercises.iterator(); iterator.hasNext();) {
			Exercise exercise = (Exercise) iterator.next();
			if (exercise.getExerciseID() == exerciseID) {
				iterator.remove();
			}
		}
	}
	
	
	
	public Exercise getExerciseRandom(int exerciseID) {
		
	    Random randomGenerator = new Random();
	    
	      int randomInt = randomGenerator.nextInt(exercises.size());

		for (Iterator<Exercise> iterator = exercises.iterator(); iterator.hasNext();) {
			Exercise exercise = (Exercise) iterator.next();
			if (exercise.getExerciseID() == randomInt) {
				return exercise;
			}
		}
		return null;

	}

	public List<Exercise> getExercisesDificulty(String dificulty) {

		List<Exercise> filteredGames = new ArrayList<Exercise>();
		for (Iterator<Exercise> iterator = exercises.iterator(); iterator.hasNext();) {
			Exercise exercise = (Exercise) iterator.next();
			if(exercise.getDificulty().equals(dificulty))
				filteredGames.add(exercise);
		}		
		return filteredGames;
		
	}
	
	public List<Exercise> getExercisesDuration(int duration) {

		List<Exercise> filteredGames = new ArrayList<Exercise>();
		for (Iterator<Exercise> iterator = exercises.iterator(); iterator.hasNext();) {
			Exercise exercise = (Exercise) iterator.next();
			if(exercise.getDuration() == duration)
				filteredGames.add(exercise);
		}		
		return filteredGames;
		
	}
	
//	public List<Exercise> getExercisesTag(String tag) {
//
//		List<Exercise> filteredGames = new ArrayList<Exercise>();
//		for (Iterator<Exercise> iterator = exercises.iterator(); iterator.hasNext();) {
//			Exercise exercise = (Exercise) iterator.next();
//			if(exercise.getDificulty().equals(tag))
//				filteredGames.add(exercise);
//		}		
//		return filteredGames;
//		
//	}
//	
//	public List<Exercise> getExercisesLanguage(String language) {
//
//		List<Exercise> filteredGames = new ArrayList<Exercise>();
//		for (Iterator<Exercise> iterator = exercises.iterator(); iterator.hasNext();) {
//			Exercise exercise = (Exercise) iterator.next();
//			if(exercise.getDificulty().equals(language))
//				filteredGames.add(exercise);
//		}		
//		return filteredGames;
//		
//	}
	
	
	
	
	
	
	

}

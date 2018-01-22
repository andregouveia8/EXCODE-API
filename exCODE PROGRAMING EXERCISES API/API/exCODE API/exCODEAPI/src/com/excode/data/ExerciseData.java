package com.excode.data;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.excode.model.Exercise;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ExerciseData {

	static ExerciseData exerciseData = null;
	static ErrorData errorData = ErrorData.getInstance();
	static MongoCollection<Exercise> colExercises;

	// INSTANCE
	public static ExerciseData getInstance() {
		if (exerciseData == null) {
			exerciseData = new ExerciseData();

			CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
					fromProviders(PojoCodecProvider.builder().automatic(true).build()));
			MongoClient mongoClient = new MongoClient("localhost",
					MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
			MongoDatabase dbExercise = mongoClient.getDatabase("db");
			colExercises = dbExercise.getCollection("Exercise", Exercise.class);
		}
		return exerciseData;
	}

	// GET ALL EXERCISES
	public List<Exercise> getExercises() {
		final List<Exercise> exercises = new ArrayList<Exercise>();
		Block<Exercise> printBlock = new Block<Exercise>() {
			public void apply(final Exercise exercise) {
				exercises.add(exercise);
			}
		};

		colExercises.find().forEach(printBlock);
		return exercises;
	}

	// GET EXERCISE by EXERCISEID
	public List<Exercise> getExercises_ID(String exerciseID) {
		final List<Exercise> exercises = new ArrayList<Exercise>();
		Block<Exercise> printBlock = new Block<Exercise>() {
			public void apply(final Exercise exercise) {
				exercises.add(exercise);
			}
		};

		colExercises.find(eq("exerciseID", exerciseID)).forEach(printBlock);
		return exercises;

	}

	// GET ALL EXERCISES by DIFICULTY
	public List<Exercise> getExercisesDificulty(String dificulty) {
		final List<Exercise> exercises = new ArrayList<Exercise>();
		Block<Exercise> printBlock = new Block<Exercise>() {
			public void apply(final Exercise exercise) {
				exercises.add(exercise);
			}
		};
		colExercises.find(eq("dificulty", dificulty)).forEach(printBlock);

		return exercises;
	}

	// GET ALL EXERCISES by TAG
	public List<Exercise> getExercisesTag(String tag) {
		final List<Exercise> exercises = new ArrayList<Exercise>();
		Block<Exercise> printBlock = new Block<Exercise>() {
			public void apply(final Exercise exercise) {
				exercises.add(exercise);
			}
		};
		colExercises.find(eq("tags", tag)).forEach(printBlock);

		return exercises;
	}

	// GET ALL EXERCISES by DURATION
	public List<Exercise> getExercisesDuration(int duration) {
		final List<Exercise> exercises = new ArrayList<Exercise>();
		Block<Exercise> printBlock = new Block<Exercise>() {
			public void apply(final Exercise exercise) {
				exercises.add(exercise);
			}
		};
		colExercises.find(eq("duration", duration)).forEach(printBlock);

		return exercises;
	}

	// GET ALL EXERCISES by LANGUAGE
	public List<Exercise> getExercisesLanguage(String language) {
		final List<Exercise> exercises = new ArrayList<Exercise>();
		Block<Exercise> printBlock = new Block<Exercise>() {
			public void apply(final Exercise exercise) {
				exercises.add(exercise);
			}
		};
		colExercises.find(eq("solution.language", language)).forEach(printBlock);

		return exercises;
	}

	// GET ALL EXERCISES by USER
	public List<Exercise> getExercisesUser(String userID) {
		final List<Exercise> exercises = new ArrayList<Exercise>();
		Block<Exercise> printBlock = new Block<Exercise>() {

			public void apply(final Exercise exercise) {
				exercises.add(exercise);
			}
		};
		colExercises.find(eq("author.userID", userID)).forEach(printBlock);
		return exercises;
	}

	// REMOVE EXERCISE
	public Response removeExercise(String exerciseID) {
		colExercises.deleteOne(eq("exerciseID", exerciseID));
		return null;
	}

	public void createExercise(Exercise exercise) {
		if (colExercises.find(eq("exerciseID", exercise.getExerciseID())).first() == null) {
			colExercises.insertOne(exercise);
		}
	}

}

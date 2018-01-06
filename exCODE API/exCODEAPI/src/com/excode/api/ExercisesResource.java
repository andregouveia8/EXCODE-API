package com.excode.api;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.excode.imp.ExerciseManager;
import com.excode.model.Exercise;

@Path("/exercises")
public class ExercisesResource {

	// GET EXERCISES BY DIFICULTY
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Exercise> getGamesDificulty(@QueryParam("dificulty") String dificulty) {

		ExerciseManager exerciseManager = ExerciseManager.getInstance();
		if (dificulty != null) {
			return exerciseManager.getExercisesDificulty(dificulty);
		}
		return exerciseManager.getExercises();
	}
//	// GET EXERCISES BY DURATION
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<Exercise> getGamesDuration(@QueryParam("duration") int duration) {
//
//		ExerciseManager exerciseManager = ExerciseManager.getInstance();
//		if (duration > 0) {
//			return exerciseManager.getExercisesDuration(duration);
//		}
//		return exerciseManager.getExercises();
//	}

	// GET A SPECIFIC EXERCISE
	@Path("/{exerciseID}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Exercise getExercise(@PathParam("exerciseID") int exerciseID) {

		ExerciseManager ex = ExerciseManager.getInstance();
		return ex.getExercise(exerciseID);

	}

	// DELETE A SPECIFIC EXERCISE
	@Path("/{exerciseID}")
	@DELETE
	public Response removeExercise(@PathParam("exerciseID") int exerciseID) {
		ExerciseManager ex = ExerciseManager.getInstance();
		ex.removeExercise(exerciseID);
		return Response.ok().entity("Exercise removed!").build();
	}

	// // GET A RANDOM EXERCISE
	// @Path("exerciseID/random")
	// @GET
	// @Produces(MediaType.APPLICATION_JSON)
	// public Exercise getExerciseRandom(@PathParam("exerciseID") int exerciseID) {
	//
	// ExerciseManager ex = ExerciseManager.getInstance();
	// return ex.getExercise(exerciseID);
	//
	// }

//	// GET ALL EXERCISES DIFICULTY == X
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//
//	public List<Exercise> getGames() {
//
//		ExerciseManager gm = ExerciseManager.getInstance();
//		if (dificulty != null) {
//			return gm.getExercises(dificulty);
//		}
//		return gm.getExercises();
//	}

}
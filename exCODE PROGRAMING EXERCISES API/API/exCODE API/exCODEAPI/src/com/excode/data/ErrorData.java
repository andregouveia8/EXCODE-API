package com.excode.data;

import static com.mongodb.client.model.Filters.*;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.ArrayList;
import java.util.List;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.excode.model.Failure;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ErrorData {
	
	static ErrorData errorData = null;
	static MongoCollection<Failure> colErrors;

	ExerciseData gameData = ExerciseData.getInstance();

	public static ErrorData getInstance() {
		if (errorData == null) {
			errorData = new ErrorData();

			CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
					fromProviders(PojoCodecProvider.builder().automatic(true).build()));
			MongoClient mongoClient = new MongoClient("localhost",
					MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
			MongoDatabase dbError = mongoClient.getDatabase("db");
			colErrors = dbError.getCollection("Error", Failure.class);
		}
		return errorData;
	}

	public List<Failure> getErrors() {
			final List<Failure> failures = new ArrayList<Failure>();
			Block<Failure> printBlock = new Block<Failure>() {
				public void apply(final Failure failure) {
					failures.add(failure);
				}
			};
			colErrors.find().forEach(printBlock);
			return failures;
	}
	
	public List<Failure> getErrorID(int errorID) {
		final List<Failure> failures = new ArrayList<Failure>();
		Block<Failure> printBlock = new Block<Failure>() {
			public void apply(final Failure failure) {
				failures.add(failure);
			}
		};
		colErrors.find(eq("errorID",errorID)).forEach(printBlock);
		return failures;
}
}

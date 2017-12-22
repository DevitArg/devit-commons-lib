package com.devit.commons.retryservice.mongo.emulate;

/**
 * @author Lucas.Godoy on 21/12/17.
 */
public class MongoFakeRepository {

	public FakeDocument save(FakeDocument fakeDocument) {
		return new FakeDocument();
	}

}

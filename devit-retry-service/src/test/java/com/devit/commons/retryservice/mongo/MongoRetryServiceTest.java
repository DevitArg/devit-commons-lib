package com.devit.commons.retryservice.mongo;

import com.devit.commons.retryservice.generic.RetryProperties;
import com.devit.commons.retryservice.mongo.emulate.FakeDocument;
import com.devit.commons.retryservice.mongo.emulate.MongoFakeRepository;
import com.devit.commons.retryservice.mongo.emulate.MongoProperties;
import com.mongodb.MongoSocketReadException;
import com.mongodb.MongoTimeoutException;
import com.mongodb.ServerAddress;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.data.mongodb.UncategorizedMongoDbException;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.support.RetryTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * @author Lucas.Godoy on 21/12/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class MongoRetryServiceTest {

	private final static Long RETRY_BACK_OFF_TIME_MS = 1000L;
	private final static Integer RETRY_MAX_ATTEMPTS = 3;

	private MongoFakeRepository mongoFakeRepository;

	private MongoRetryTemplateConfiguration mongoRetryTemplateConfiguration;
	private RetryTemplate mongoRetryTemplate;

	@Before
	public void setUp() {
		RetryProperties retryProperties = new RetryProperties();
		retryProperties.setRetryMaxAttempts(RETRY_MAX_ATTEMPTS);
		retryProperties.setRetryBackOffTime(RETRY_BACK_OFF_TIME_MS);

		MongoProperties mongoProperties = new MongoProperties();
		mongoProperties.setRetryProperties(retryProperties);

		mongoRetryTemplateConfiguration = new MongoRetryTemplateConfiguration(mongoProperties);

		mongoRetryTemplate = mongoRetryTemplateConfiguration.retryTemplate();

		mongoFakeRepository = mock(MongoFakeRepository.class);
	}

	@Test
	public void exhaustedDoWithRetryAfterExceptionsBeingThrown_test() {
		getRetryableExceptionsList().forEach(exception -> {
			FakeDocument fakeDocument = new FakeDocument();
			when(mongoFakeRepository.save(fakeDocument))
					.thenThrow(exception)
					.thenThrow(exception)
					.thenThrow(exception);

			Throwable throwable = catchThrowable(() ->
					mongoRetryTemplate.execute(getRetryCallBack(fakeDocument)));

			assertThat(throwable).isInstanceOf(exception.getClass());
			verify(mongoFakeRepository, times(RETRY_MAX_ATTEMPTS)).save(fakeDocument);
		});
	}

	@Test
	public void exhaustedDoWithRetryAfterNotConfiguredExceptionsBeingThrown_test() {
		FakeDocument fakeDocument = new FakeDocument();
		when(mongoFakeRepository.save(fakeDocument))
				.thenThrow(new IllegalArgumentException())
				.thenThrow(new IllegalArgumentException())
				.thenThrow(new IllegalArgumentException());

		Throwable throwable = catchThrowable(() ->
				mongoRetryTemplate.execute(getRetryCallBack(fakeDocument)));

		assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
		verify(mongoFakeRepository, times(1)).save(fakeDocument);
	}

	private RetryCallback<FakeDocument, Throwable> getRetryCallBack(FakeDocument document) {
		return context -> mongoFakeRepository.save(document);
	}

	private List<Throwable> getRetryableExceptionsList() {
		List<Throwable> retryableExceptions = new ArrayList<>();
		retryableExceptions.add(new DataAccessResourceFailureException("DataAccessResourceFailureException"));
		retryableExceptions.add(new UncategorizedMongoDbException("UncategorizedMongoDbException", new Exception()));
		retryableExceptions.add(new MongoSocketReadException("MongoSocketReadException", new ServerAddress("host")));
		retryableExceptions.add(new MongoTimeoutException("MongoTimeoutException"));
		return retryableExceptions;
	}

}

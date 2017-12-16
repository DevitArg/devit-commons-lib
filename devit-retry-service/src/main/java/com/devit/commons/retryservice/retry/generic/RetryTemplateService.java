package com.devit.commons.retryservice.retry.generic;

import org.springframework.retry.RetryCallback;
import org.springframework.retry.support.RetryTemplate;

/**
 * @author Lucas.Godoy on 15/12/17.
 */
public abstract class RetryTemplateService<R extends RetryForResult, E extends Exception> implements IRetryTemplateService<R, E> {

	protected RetryTemplate retryTemplate;

	public RetryTemplateService(RetryTemplate retryTemplate) {
		this.retryTemplate = retryTemplate;
	}

	protected <R extends RetryForResult, E extends Exception> R doWithRetryOrThrowCheckedException(RetryCallback<R, Throwable> retryCallback) throws E {
		try {
			return retryTemplate.execute(retryCallback);
		} catch (Throwable throwable) {
			throw (E) getCheckedException();
		}
	}

}

package com.devit.commons.retryservice.generic;

import org.springframework.retry.RetryCallback;
import org.springframework.retry.support.RetryTemplate;

/**
 * @author Lucas.Godoy on 15/12/17.
 */
public abstract class RetryService<R extends IRetryForResult> implements IRetryTemplateService {

	protected RetryTemplate retryTemplate;

	public RetryService(RetryTemplate retryTemplate) {
		this.retryTemplate = retryTemplate;
	}

	public <R extends IRetryForResult> R doWithRetryOrThrowCheckedException(RetryCallback<R, Throwable> retryCallback)
			throws Throwable {
		return retryTemplate.execute(retryCallback);
	}

}

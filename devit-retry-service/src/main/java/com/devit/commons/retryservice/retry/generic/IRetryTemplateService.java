package com.devit.commons.retryservice.retry.generic;

/**
 * @author Lucas.Godoy on 16/12/17.
 */
public interface IRetryTemplateService<R extends RetryForResult, E extends Exception> {

	<E extends Exception> E getCheckedException();

}

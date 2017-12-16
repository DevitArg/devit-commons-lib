package com.devit.commons.retryservice.retry.generic;

import org.springframework.retry.RetryPolicy;
import org.springframework.retry.backoff.BackOffPolicy;

/**
 * @author Lucas.Godoy on 14/12/17.
 */
public interface RetryTemplateComponentsConfiguration {

	RetryPolicy retryPolicy();

	BackOffPolicy backOffPolicy();

}

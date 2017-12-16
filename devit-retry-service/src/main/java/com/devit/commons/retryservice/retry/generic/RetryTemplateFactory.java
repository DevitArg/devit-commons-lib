package com.devit.commons.retryservice.retry.generic;

import org.springframework.context.annotation.Bean;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.backoff.BackOffPolicy;
import org.springframework.retry.backoff.StatelessBackOffPolicy;
import org.springframework.retry.policy.ExceptionClassifierRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Lucas.Godoy on 14/12/17.
 */
public class RetryTemplateFactory {

	@Bean
	public RetryTemplate getRetryTemplate(RetryPolicy retryPolicy, BackOffPolicy backOffPolicy) {
		RetryTemplate retryTemplate = new RetryTemplate();
		retryTemplate.setRetryPolicy(retryPolicy);
		retryTemplate.setBackOffPolicy(backOffPolicy);
		return retryTemplate;
	}

}

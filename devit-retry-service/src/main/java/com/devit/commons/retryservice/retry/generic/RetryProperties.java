package com.devit.commons.retryservice.retry.generic;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Lucas.Godoy on 14/12/17.
 */
@ConfigurationProperties(prefix = "retry")
public class RetryProperties {

	private Long retryBackOffTime;
	private Integer retryMaxAttempts;

	public Integer getRetryMaxAttempts() {
		return retryMaxAttempts;
	}

	public void setRetryMaxAttempts(Integer retryMaxAttempts) {
		this.retryMaxAttempts = retryMaxAttempts;
	}

	public Long getRetryBackOffTime() {
		return retryBackOffTime;
	}

	public void setRetryBackOffTime(Long retryBackOffTime) {
		this.retryBackOffTime = retryBackOffTime;
	}

}

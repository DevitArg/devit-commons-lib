package com.devit.commons.retryservice.generic;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Lucas.Godoy on 14/12/17.
 */
@Configuration
@ConfigurationProperties(prefix = "retryPropertiesgit ")
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

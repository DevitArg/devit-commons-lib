package com.devit.commons.retryservice.mongo.emulate;

import com.devit.commons.retryservice.generic.IConfigurableRetryProperties;
import com.devit.commons.retryservice.generic.RetryProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Lucas.Godoy on 21/12/17.
 */
@Configuration("mongoRetryProperties")
public class MongoProperties implements IConfigurableRetryProperties {

	private String someConfig = "someValue";
	private RetryProperties retryProperties;

	public String getSomeConfig() {
		return someConfig;
	}

	public void setSomeConfig(String someConfig) {
		this.someConfig = someConfig;
	}

	@Override
	public RetryProperties getRetryProperties() {
		return this.retryProperties;
	}

	@Override
	public void setRetryProperties(RetryProperties retryProperties) {
		this.retryProperties = retryProperties;
	}
}

package com.mg.api.core.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApiConfiguration {
	@Value("#{configProperties['domain.url']}")
	private String domainUrl;

	public String getDomainUrl() {
		return domainUrl;
	}

	public void setDomainUrl(String domainUrl) {
		this.domainUrl = domainUrl;
	}
}

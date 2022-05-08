package com.cognizant.game._config;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "jdoodle")
public class JdoodleConfig {

	Logger logger = LogManager.getLogger(JdoodleConfig.class);
	
	private String baseUrl;
	private String clientId;
	private String clientSecret;

	@PostConstruct
	public void log() {
		logger.info("Doodle configuration loaded baseUrl: {}, clientId:{}, clientSecret: {}", this.baseUrl, this.clientId,
				this.clientSecret);
	}

}

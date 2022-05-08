package com.cognizant.game.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 
 * @author zammelib
 *
 */

@Data
public class ExecuteRequest {

	@JsonProperty("clientId")
	private String clientId;

	@JsonProperty("clientSecret")
	private String clientSecret;

	@JsonProperty("script")
	private String script;

	@JsonProperty("stdin")
	private String stdin;

	@JsonProperty("language")
	private String language;

	@JsonProperty("versionIndex")
	private String versionIndex;

}

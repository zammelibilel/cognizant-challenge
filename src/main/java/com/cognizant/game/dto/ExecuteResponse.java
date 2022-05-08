package com.cognizant.game.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 
 * @author zammelib
 *
 */

@Data
public class ExecuteResponse {

	@JsonProperty("output")
	private String output;

	@JsonProperty("memory")
	private BigDecimal memory;

	@JsonProperty("cpuTime")
	private BigDecimal cpuTime;

	@JsonProperty("statusCode")
	private Integer statusCode;

}

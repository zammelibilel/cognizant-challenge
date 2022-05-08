package com.cognizant.game.dto;

import lombok.Data;

/**
 * 
 * @author zammelib
 *
 */
@Data
public class TestRequest {

	private String player;
	private Long taskId;
	private String stdin;
	private String solution;

}

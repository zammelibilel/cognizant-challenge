package com.cognizant.game.model;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 * @author zammelib
 *
 */
@Data
public class TestCountResult implements Serializable {

	private static final long serialVersionUID = 1L;

	private String player;

	private Long totalSuccessTests;

	private String tasks;

	public TestCountResult(String player, Long totalSuccessTests, String tasks) {
		super();
		this.player = player;
		this.totalSuccessTests = totalSuccessTests;
		this.tasks = tasks;
	}

}

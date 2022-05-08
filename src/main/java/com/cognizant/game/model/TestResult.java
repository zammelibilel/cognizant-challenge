package com.cognizant.game.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.cognizant.game.Enum.TestResultTypeEnum;

import lombok.Data;

/**
 * 
 * @author zammelib
 *
 */
@Data
@Entity
@Table(name = "TEST_RESULT")
public class TestResult implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "PLAYER_NAME")
	private String player;
	
	@OneToOne(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "TASK_ID")
	private Task task;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "RESULT")
	private TestResultTypeEnum result;

}

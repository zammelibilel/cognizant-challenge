package com.cognizant.game.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author zammelib
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "TEST_CASE")
public class TestCase implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "TASK_ID")
	private Task task;

	@Column(name = "INPUT")
	private String input;
	
	@Column(name = "OUTPUT")
	private String output;

}

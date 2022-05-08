package com.cognizant.game.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.game.dto.ExecuteResponse;
import com.cognizant.game.dto.TestRequest;
import com.cognizant.game.model.Task;
import com.cognizant.game.model.TestCountResult;
import com.cognizant.game.service.TaskService;
import com.cognizant.game.service.TestResultService;

@RestController
@RequestMapping(value = "/game")
public class GameController {

	@Autowired
	TaskService taskService;

	@Autowired
	TestResultService testResultService;

	/**
	 * Submit test
	 * @param request
	 * @return return the execution result : true for success test
	 */
	@PostMapping("/commit")
	@ResponseStatus(HttpStatus.OK)
	public Boolean executeAndCommitTest(@RequestBody TestRequest request) {
		return taskService.executeAndCommitTest(request);
	}
	
	/**
	 * Submit test
	 * @param request
	 * @return return the execution result : true for success test
	 */
	@PostMapping("/execute")
	@ResponseStatus(HttpStatus.OK)
	public ExecuteResponse executeTest(@RequestBody TestRequest request) {
		return taskService.executeTest(request);
	}

	/**
	 * Produces a list of 3 top players tests result
	 * 
	 * @return the player tests result to display
	 */
	@GetMapping("/players")
	@ResponseStatus(code = HttpStatus.OK)
	public List<TestCountResult> getTopPlayersTestsResult() {
		return testResultService.getTestResults();
	}

	/**
	 * Produces a list tasks
	 * 
	 * @return 
	 */
	@GetMapping("/tasks")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Task> getTasks() {
		return taskService.getTasks();
	}

}

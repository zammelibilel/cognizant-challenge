package com.cognizant.game.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.game.Enum.TestResultTypeEnum;
import com.cognizant.game.dto.ExecuteRequest;
import com.cognizant.game.dto.ExecuteResponse;
import com.cognizant.game.dto.TestRequest;
import com.cognizant.game.model.Task;
import com.cognizant.game.model.TestResult;
import com.cognizant.game.repository.TaskRepository;
import com.cognizant.game.repository.TestResultRepository;

@Service
public class TaskService {

	Logger logger = LogManager.getLogger(TaskService.class);

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private TestResultRepository testResultRepository;

	@Autowired
	private JdoodleClient jdoodleClient;

	public List<Task> getTasks() {
		return taskRepository.findAll();
	}

	public Boolean executeAndCommitTest(TestRequest request) {
		Boolean result = false;

		Task task = taskRepository.getById(request.getTaskId());

		if (task != null) {

			ExecuteRequest testInput = new ExecuteRequest();
			testInput.setLanguage("java");
			testInput.setVersionIndex("0");
			testInput.setScript(request.getSolution());
			testInput.setStdin(task.getInput());

			try {

				// 1 - call the doodle api to execute the script
				ExecuteResponse response = jdoodleClient.executeTest(testInput);

				if (response != null) {

					// 2 - check/compare api result with the existing task output

					String resultOutput = response.getOutput().replace("\n", "");
					if (resultOutput.equals(task.getOutput())) {
						result = true;
					} else {
						logger.warn("> Wrong result [Task: {}, Output: {}]", task.getName(), response.getOutput());
					}

					// 3 - save test result
					TestResult testResult = new TestResult();
					testResult.setPlayer(request.getPlayer());
					testResult.setResult(result ? TestResultTypeEnum.SUCCESS : TestResultTypeEnum.FAILED);
					testResult.setTask(task);

					testResultRepository.save(testResult);

				}

			} catch (Exception e) {
				logger.error("> Error when executing script using doodle API [Script: {}, Language: {}]",
						testInput.getScript(), testInput.getLanguage(), e);
			}

		}

		return result;
	}

	public ExecuteResponse executeTest(TestRequest request) {

		ExecuteResponse result = null;

		ExecuteRequest testInput = new ExecuteRequest();
		testInput.setLanguage("java");
		testInput.setVersionIndex("0");
		testInput.setScript(request.getSolution());
		testInput.setStdin(request.getStdin());

		try {

			// call the doodle api to execute the script
			result = jdoodleClient.executeTest(testInput);

		} catch (Exception e) {
			logger.error("> Error when executing script using doodle API [Script: {}, Language: {}]",
					testInput.getScript(), testInput.getLanguage(), e);
		}

		return result;
	}

}

package com.cognizant.game.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.cognizant.game.model.TestCountResult;
import com.cognizant.game.repository.TestResultRepository;

@Service
public class TestResultService {

	Logger logger = LogManager.getLogger(TestResultService.class);

	@Autowired
	private TestResultRepository testResultRepository;

	public List<TestCountResult> getTestResults() {

		List<TestCountResult> tests = testResultRepository.countTotalSuccessfullTestByPlayer(PageRequest.of(0, 3));

		return tests.stream().map(test -> removeDuplicatesTask(test)).collect(Collectors.toList());

	}

	private TestCountResult removeDuplicatesTask(TestCountResult test) {

		String result = null;

		String tasks = test.getTasks();

		if (!ObjectUtils.isEmpty(tasks)) {

			// converting comma separate String to List of String
			List<String> taskList = Arrays.asList(tasks.split(","));

			// remove duplicate tasks from list
			result = taskList.stream().distinct().collect(Collectors.toList()).toString();
		}

		test.setTasks(result);

		return test;

	}

}

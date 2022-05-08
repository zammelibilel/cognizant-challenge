package com.cognizant.game.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cognizant.game.model.TestCountResult;
import com.cognizant.game.model.TestResult;

/**
 * repository to manage test result entries from sqlite db
 * 
 * @author zammelib
 *
 */
public interface TestResultRepository extends JpaRepository<TestResult, Long> {

	@Query("SELECT new com.cognizant.game.model.TestCountResult(test.player, COUNT(*), GROUP_CONCAT(task.name)) "
			+ "FROM TestResult AS test, Task AS task WHERE test.task=task.id and test.result='SUCCESS' GROUP BY test.player ORDER BY COUNT(*) DESC")
	List<TestCountResult> countTotalSuccessfullTestByPlayer(Pageable pageable);

}
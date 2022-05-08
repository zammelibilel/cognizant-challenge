package com.cognizant.game.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.game.model.Task;

/**
 * repository to manage task entries from sqlite db
 * 
 * @author zammelib
 *
 */
public interface TaskRepository extends JpaRepository<Task, Long> {

}

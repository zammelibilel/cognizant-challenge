package com.cognizant.game.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.cognizant.game.dto.TestRequest;
import com.cognizant.game.model.Task;
import com.cognizant.game.service.TaskService;


/**
 * Test class to test the endpoints provided by GameController  
 * @author zammelib
 *
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class GameControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private TaskService taskServiceMock;
	
	
    @BeforeEach
    public void setUp() {

        // mock the task repository to provide a list of 3 tasks.
       List<Task>  taskList = new ArrayList<>();
        taskList.add(new Task(1L, "task1", "desc t1", "true", "true"));
        taskList.add(new Task(1L, "task2", "desc t2", "true", "true"));
        taskList.add(new Task(1L, "task2", "desc t3", "true", "true"));

        Mockito.when(taskServiceMock.getTasks()).thenReturn(taskList);
        
		Mockito.when(taskServiceMock.executeAndCommitTest(any(TestRequest.class))).thenReturn(Boolean.TRUE);

    }
	@Test
	@DisplayName("When all tasks are requested then they are all returned")
	public void tasksRequested() throws Exception {
		mockMvc.perform(get("/game/tasks"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(3)));
	}
	
	@Test
	public void should_executeAndCommitTest_and_return_false() throws Exception {

		mockMvc.perform(post("/game/commit")
	           .contentType(MediaType.APPLICATION_JSON)
	           .content("{ \"taskId\": 1}") 
	           .accept(MediaType.APPLICATION_JSON))
	           .andExpect(status().isOk())
	           .andExpect(content().string("true"));
	}

}

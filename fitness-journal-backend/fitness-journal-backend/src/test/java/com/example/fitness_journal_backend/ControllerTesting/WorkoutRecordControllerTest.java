package com.example.fitness_journal_backend.ControllerTesting;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;


import com.example.fitness_journal_backend.Controllers.WorkoutRecordController;
import com.example.fitness_journal_backend.Entities.WorkoutRecord;
import com.example.fitness_journal_backend.Services.WorkoutRecordService;
// used this for the testing implementation of test: https://thepracticaldeveloper.com/guide-spring-boot-controller-tests/#strategy-1-spring-mockmvc-example-in-standalone-mode
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.fitness_journal_backend.ControllerAdvice.WorkoutRecordExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class WorkoutRecordControllerTest {

    

    @MockBean
    private WorkoutRecordService wrs;

    @InjectMocks
    private WorkoutRecordController workoutRecordController;

    private  JacksonTester<WorkoutRecord> jsonWorkoutRecord;

    private MockMvc mvc;
    @BeforeEach
    public void setup(){
        JacksonTester.initFields(this, new ObjectMapper());
        //TODO: Create the controller advice class and filter class testing.

         mvc= MockMvcBuilders.standaloneSetup(workoutRecordController)
         .setControllerAdvice(new WorkoutRecordExceptionHandler())
         .addFilters()
         .build();
    }

    @Test
    public void createWorkoutRecordTest(WorkoutRecord w) throws Exception{
        MockHttpServletResponse response= mvc.perform(
                post("/").contentType(MediaType.APPLICATION_JSON).content(jsonWorkoutRecord.write(new WorkoutRecord()).getJson())
        ).andReturn().getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }
}

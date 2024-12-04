package com.example.fitness_journal_backend.ControllerTesting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.example.fitness_journal_backend.Controllers.WorkoutRecordController;

import com.example.fitness_journal_backend.Services.WorkoutRecordService;
// used this for the testing implementation of test: https://thepracticaldeveloper.com/guide-spring-boot-controller-tests/#strategy-1-spring-mockmvc-example-in-standalone-mode
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = FitnessJournalBackendApplication.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(WorkoutRecordController.class)
@WithMockUser(username = "user",roles = {"USER"}) // needed for security reason might delete later
public class WorkoutRecordControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private WorkoutRecordService wrs;

//    @InjectMocks
//    private WorkoutRecordController;
//
//    private  JacksonTester<WorkoutRecord> jsonWorkoutRecord;



    @Test
    public void getAllWorkoutRecordsAPI() throws Exception{
            //then
            mvc.perform(MockMvcRequestBuilders
                            .get("/api/v1/records")
                            .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$").exists());
            // database is empty for this test
//                    .andExpect(MockMvcResultMatchers.jsonPath("$.[*].id").isNotEmpty());

        }
}


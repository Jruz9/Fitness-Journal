package com.example.fitness_journal_backend.ControllerTesting;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.fitness_journal_backend.Controllers.WorkoutRecordController;
import com.example.fitness_journal_backend.Entities.WorkoutRecord;
import com.example.fitness_journal_backend.Services.WorkoutRecordService;
// used this for the testing implementation of test: https://thepracticaldeveloper.com/guide-spring-boot-controller-tests/#strategy-1-spring-mockmvc-example-in-standalone-mode

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class WorkoutRecordControllerTest {

    

    @MockBean
    private WorkoutRecordService wrs;

    @InjectMocks
    private WorkoutRecordController workoutRecordController;

    @Test
    public void createWOrkoutRecordTest(WorkoutRecord w) throws Exception{
        
    }
}

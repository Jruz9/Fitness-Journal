package com.example.fitness_journal_backend.ControllerTesting;

import com.example.fitness_journal_backend.Controllers.WorkoutController;
import com.example.fitness_journal_backend.Entities.Workout;
import com.example.fitness_journal_backend.Entities.WorkoutRecord;
import com.example.fitness_journal_backend.Services.WorkoutRecordService;
import com.example.fitness_journal_backend.Services.WorkoutService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(WorkoutController.class)
@WithMockUser(username = "user",roles = {"USER"})
public class WorkoutControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private WorkoutService service;

    @MockBean
    private WorkoutRecordService workoutRecordService;


    @Test
    public void getAllWorkoutAPITest()throws Exception{
        mvc.perform(MockMvcRequestBuilders
                .get("/api/v1/workouts")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists());
    }

    @Test
    public void createWorkoutAPITest() throws  Exception{
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/workoutRecord/{workoutRecordId}/workout",1)
                .with(csrf()).content(asJsonString(new Workout(1.00,20.0,3,6,1L, LocalDate.now(),workoutRecordService.findByWorkoutRecordId(1L).get())))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    private static  String asJsonString(final Object obj){
        try{
            return new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(obj);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Test
    public  void updateWorkoutAPITest()throws Exception{
        mvc.perform(MockMvcRequestBuilders.put("/api/v1/workouts/{id}",1).with(csrf())
                .content(asJsonString(new Workout(1.00,30.0,4,6,1L, LocalDate.now(),workoutRecordService.findByWorkoutRecordId(1L).get())))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public  void deleteWorkoutAPITest() throws Exception{
        mvc.perform(MockMvcRequestBuilders
                .delete("/api/v1/workouts/{id}",1).with(csrf()))
                .andExpect(status().isOk());
    }

}

package com.example.fitness_journal_backend.ControllerTesting;

import com.example.fitness_journal_backend.Entities.Workout;
import com.example.fitness_journal_backend.Entities.WorkoutRecord;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
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
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = FitnessJournalBackendApplication.class)
/*
References
https://howtodoinjava.com/spring-boot2/testing/spring-boot-mockmvc-example/
https://www.baeldung.com/spring-boot-testing


*/
@ExtendWith(SpringExtension.class)
@WebMvcTest(WorkoutRecordController.class)
@WithMockUser(username = "user",roles = {"USER"}) // needed for security reason might delete later
public class WorkoutRecordControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private WorkoutRecordService wrs;

static final List<Workout> exampleWorkout= List.of(new Workout(20.0,20.0,3,12,10L,LocalDate.now()));
static final WorkoutRecord exampleRecord= new WorkoutRecord(1L, LocalDate.now(),"push ups", exampleWorkout);

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
        @Test
    public  void CreateWorkoutRecordAPI() throws Exception{
        mvc.perform(MockMvcRequestBuilders
                .post("/api/v1/records/")
                        .with(csrf())
                .content(asJsonString(exampleRecord))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
//                .andReturn().getResponse().getContentAsString();
                .andExpect(status().isOk());
//                .andReturn().getResponse().getContentAsString();
//                .andExpect(MockMvcResultMatchers.jsonPath("data.id").exists());
        }

        public static  String asJsonString(final Object obj){
        try{
            return new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(obj);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        }

        @Test
        public void updateWorkoutRecordAPITest()throws Exception{
        WorkoutRecord oldRecord= new WorkoutRecord(1L,LocalDate.now(),"Bench Press",exampleWorkout);
        WorkoutRecord newRecord=  new WorkoutRecord(1L,LocalDate.now(),"Cycling",exampleWorkout);
            Mockito.when(wrs.findByWorkoutRecordId(1L)).thenReturn(Optional.of(oldRecord));
            Mockito.when(wrs.saveWorkoutRecord(oldRecord)).thenReturn(oldRecord);


        mvc.perform(MockMvcRequestBuilders
                .put("/api/v1/records/{}",1L)
                .with(csrf())
                .content(asJsonString(new WorkoutRecord(2L,LocalDate.now(),"sit ups",List.of(new Workout()))))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        }

        @Test
        public void deleteWorkoutRecordAPITest()throws Exception{
        WorkoutRecord deleteRecord= new WorkoutRecord();
        deleteRecord.setId(1L);
        deleteRecord.setWorkoutName("Push Up");
        deleteRecord.setWorkoutDate(LocalDate.now());
        deleteRecord.setWorkoutData(exampleWorkout);
        Mockito.when(wrs.findByWorkoutRecordId(1L)).thenReturn(Optional.of(deleteRecord));
        
        mvc.perform(MockMvcRequestBuilders
                .delete("/api/v1/records/{id}",1L)
                        .with(csrf())
                )
                .andDo(print())
                .andExpect(status().isOk());
        }
}


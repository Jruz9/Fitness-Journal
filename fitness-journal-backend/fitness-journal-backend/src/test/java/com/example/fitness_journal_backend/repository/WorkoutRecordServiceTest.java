package com.example.fitness_journal_backend.repository;

import com.example.fitness_journal_backend.Entities.Workout;
import com.example.fitness_journal_backend.Entities.WorkoutRecord;
import com.example.fitness_journal_backend.Services.WorkoutRecordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
public class WorkoutRecordServiceTest {

    @Autowired
    private WorkoutRecordService workoutRecordService;

    @Test
    public void makeNewWorkoutRecord(){
        Workout workout=new Workout();
        workout.setRep(10);
        workout.setSessions(5);
        workout.setWeight(10);
        workout.setDuration(2.00);

        WorkoutRecord workoutRecord= new WorkoutRecord();
        workoutRecord.setWorkoutName("Push up");
        workoutRecord.setWorkoutDate(LocalDate.now());
        workoutRecord.setWorkoutData(List.of(workout));
        WorkoutRecord savedWorkoutRecord= workoutRecordService.saveWorkoutRecord(workoutRecord);

        assertNotNull(savedWorkoutRecord);
        assertEquals(workoutRecord.getWorkoutName(),savedWorkoutRecord.getWorkoutName());
    }


}

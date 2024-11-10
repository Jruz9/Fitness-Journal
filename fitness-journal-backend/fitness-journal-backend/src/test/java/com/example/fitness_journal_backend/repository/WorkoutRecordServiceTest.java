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
import java.util.Optional;

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
        assertEquals(workoutRecord.getWorkoutData(),savedWorkoutRecord.getWorkoutData());
    }

    @Test
    public void deleteWorkoutRecord(){
        Workout workout = new Workout();
        WorkoutRecord workoutRecord= new WorkoutRecord();

        workout.setRep(10);
        workout.setSessions(5);
        workout.setWeight(10);
        workout.setDuration(2.00);
        workoutRecord.setWorkoutName("Push up");
        workoutRecord.setWorkoutDate(LocalDate.now());
        workoutRecord.setWorkoutData(List.of(workout));
        //save workout record
        workoutRecordService.saveWorkoutRecord(workoutRecord);
        //deletes workout record from database
        workoutRecordService.deleteWorkoutRecord(workoutRecord);

        Optional<WorkoutRecord> graveyard= workoutRecordService.findByWorkoutRecordId(workoutRecord.getId());
        //false if the delete worked and vice versa.
        assertFalse(graveyard.isPresent());
    }
    @Test
    public void TestFindWorkoutRecordById(){
        WorkoutRecord wkr= new WorkoutRecord();
        Workout workout= new Workout();
        workout.setRep(10);
        workout.setSessions(5);
        workout.setWeight(10);
        workout.setDuration(2.00);
        wkr.setWorkoutName("pull ups");
        wkr.setWorkoutDate(LocalDate.now());
        wkr.setWorkoutData(List.of(workout));
        workoutRecordService.saveWorkoutRecord(wkr);
        WorkoutRecord findWorkoutRecord= workoutRecordService.findByWorkoutRecordId(wkr.getId()).orElse(null);
        assertNotNull(findWorkoutRecord);
        assertEquals(wkr.getWorkoutName(),findWorkoutRecord.getWorkoutName());

    }


}

package com.example.fitness_journal_backend.repository;

import com.example.fitness_journal_backend.Entities.WorkoutRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.fitness_journal_backend.Entities.Workout;
import com.example.fitness_journal_backend.Services.WorkoutService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class WorkoutRepositoryTest {
    @Autowired
    private WorkoutService workoutService; //research if this works for out case
    @Test
    public void makeNewWorkout(){


        Workout workout=new Workout();
        workout.setId(0L);
        workout.setRep(10);
        workout.setSessions(5);
        workout.setWeight(10);
        workout.setDuration(2.00);
        workout.setCreatedWorkoutTime(LocalDate.now());

        WorkoutRecord wkr =new WorkoutRecord();
        wkr.setId(0L);
        wkr.setWorkoutName("Push up");
        wkr.setWorkoutData(List.of(workout));
        wkr.setWorkoutDate(LocalDate.now());


        workout.setWorkoutRecord(wkr);

            Workout savedWorkout= workoutService.saveWorkout(workout);

        assertNotNull(savedWorkout);
        assertEquals(workout.getRep(), savedWorkout.getRep());
        assertEquals(workout.getSessions(), savedWorkout.getSessions());
        assertEquals(workout.getDuration(),savedWorkout.getDuration());
        assertEquals(workout.getWeight(),savedWorkout.getWeight());
    }
    @Test
    public void updateWorkout(){
        Workout workout= new Workout();
        workout.setRep(3);
        workout.setSessions(3);
        workout.setDuration(2.00);
        workout.setWeight(20.0);
        workout = workoutService.saveWorkout(workout);

        //modify original workout
        workout.setSessions(5);
        workout.setRep(20);
        workout=workoutService.saveWorkout(workout);

        //assets if they are not equal to prove modification was successful.
        assertNotNull(workout);
        assertEquals(workout.getRep(),20);
        assertEquals(workout.getSessions(),5);
    }

@Test
    public void  deleteWorkout(){
        //create workout
        Workout workout= new Workout();
        workout.setRep(3);
        workout.setSessions(3);
        workout.setDuration(2.00);
        workout.setWeight(20.0);
        workoutService.saveWorkout(workout);
        //delete workout
        workoutService.deleteWorkoutFromWorkoutRecord(workout);
        Optional<Workout> deletedWorkout=workoutService.findById(workout.getId());
        //asserts false if the item is present is and true if it is empty.
        assertFalse(deletedWorkout.isPresent());
    }
@Test
    public void testFindWorkoutById(){
        Workout wk= new Workout();
    wk.setRep(3);
    wk.setSessions(3);
    wk.setDuration(2.00);
    wk.setWeight(20.0);
    workoutService.saveWorkout(wk);
    Workout foundWorkout= workoutService.findById(wk.getId()).orElse(null);

    assertNotNull(foundWorkout);
    assertEquals(wk.getSessions(),foundWorkout.getSessions());
    }
}

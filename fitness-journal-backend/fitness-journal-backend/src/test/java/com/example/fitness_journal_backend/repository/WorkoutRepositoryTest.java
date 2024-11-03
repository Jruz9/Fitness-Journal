package com.example.fitness_journal_backend.repository;

import com.example.fitness_journal_backend.Entities.WorkoutRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.fitness_journal_backend.Entities.Workout;
import com.example.fitness_journal_backend.Services.WorkoutService;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class WorkoutRepositoryTest {
    @Autowired
    private WorkoutService workoutService; //research if this works for out case
    @Test
    public void makeNewWorkout(){
        WorkoutRecord wkr =new WorkoutRecord();

        Workout workout=new Workout();
        workout.setId(0L);
        workout.setRep(10);
        workout.setSet(5);
        workout.setWeight(10);
        workout.setDuration(2.00);
        workout.setDate(LocalDate.now());
        workout.setWorkoutRecord(wkr);

        Workout savedWorkout= workoutService.saveWorkout(workout);

        assertNotNull(savedWorkout);
        assertEquals(workout.getRep(), savedWorkout.getRep());
        assertEquals(workout.getSet(), savedWorkout.getSet());
        assertEquals(workout.getDuration(),savedWorkout.getDuration());
        assertEquals(workout.getWeight(),savedWorkout.getWeight());
    }
    @Test
    public void updateWorkout(){
        Workout workout= new Workout();
        Workout unmodifiedWorkout= workoutService.saveWorkout(workout);

        //modify original workout
        workout.setRep(3);
        workout.setSet(3);
        workout.setDuration(2.00);
        workout.setWeight(20.0);

        //assets if they are not equal to prove modification was successful.
        assertNotNull(unmodifiedWorkout);
        assertNotEquals(workout.getRep(),unmodifiedWorkout.getRep());
        assertNotEquals(workout.getSet(),unmodifiedWorkout.getSet());
        assertNotEquals(workout.getDuration(),unmodifiedWorkout.getDuration());
        assertNotEquals(workout.getWeight(),unmodifiedWorkout.getWeight());
    }

@Test
    public void  deleteWorkout(){
        //create workout
        Workout workout= new Workout();
        workout.setRep(3);
        workout.setSet(3);
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
    wk.setSet(3);
    wk.setDuration(2.00);
    wk.setWeight(20.0);
    workoutService.saveWorkout(wk);
    Workout foundWorkout= workoutService.findById(wk.getId()).orElse(null);

    assertNotNull(foundWorkout);
    assertNotEquals(wk.getSet(),foundWorkout.getSet());
    }
}

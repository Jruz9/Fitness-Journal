package com.example.fitness_journal_backend.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.fitness_journal_backend.Entities.Workout;
import com.example.fitness_journal_backend.Services.WorkoutService;

@SpringBootTest
public class WorkoutRepositoryTest {
    @Autowired
    private WorkoutService workoutService; //research if this works for out case
    @Test
    public void makeNewWorkout(){
        Workout workout=new Workout();
        workout.setId(0);
        workout.setRep(10);
        workout.setSet(5);
        workout.setWeight(10);
        workout.setDuration(2.00);

        Workout savedWorkout= workoutService.saveWorkout(workout);

        assertNotNull(savedWorkout);
        assertEquals(workout.getRep(), savedWorkout.getRep());
        assertEquals(workout.getSet(), savedWorkout.getSet());

    }
}

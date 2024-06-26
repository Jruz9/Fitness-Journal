package com.example.fitness_journal_backend.Services;


import com.example.fitness_journal_backend.DAO.WorkoutRepo;
import com.example.fitness_journal_backend.Entities.Workout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * A class that allows specific services related to work out.java to work.
 */
@Service
public class WorkoutService {

    @Autowired
    public WorkoutRepo workoutRepo;

    public List<Workout>findByWorkoutId(@NonNull Long workoutID){
        return  workoutRepo.findAll(workoutID);
    }




}

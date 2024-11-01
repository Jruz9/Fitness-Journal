package com.example.fitness_journal_backend.Services;
import com.example.fitness_journal_backend.DAO.WorkoutRecordRepo;
import com.example.fitness_journal_backend.DAO.WorkoutRepo;
import com.example.fitness_journal_backend.Entities.Workout;
import com.example.fitness_journal_backend.Entities.WorkoutRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * A class that allows specific services related to work out.java to work.
 */
@Service
public class WorkoutService {

    @Autowired
    WorkoutRepo workoutRepo;
    WorkoutRecordService wks;

    public Optional<Workout>findByWorkoutId(@NonNull Long workoutID){
        return  workoutRepo.findByWorkoutId(workoutID);
    }

    public Workout saveWorkout(Workout workout){
        return workoutRepo.save(workout); // code editor is giving errors hmmm OwO
    }   
    public List<Workout> getAllWorkout(){
        return workoutRepo.findAll();
    }
    //TODO In the controller class use chain the commands here for performing the actions.
    public void deleteWorkoutFromWorkoutRecord(Workout wk){

        workoutRepo.delete(wk);
    }
}

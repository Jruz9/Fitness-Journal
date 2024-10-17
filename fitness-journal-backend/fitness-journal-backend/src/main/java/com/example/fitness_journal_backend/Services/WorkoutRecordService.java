package com.example.fitness_journal_backend.Services;

import com.example.fitness_journal_backend.DAO.WorkoutRecordRepo;
import com.example.fitness_journal_backend.Entities.WorkoutRecord;

import jakarta.annotation.Nonnull;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkoutRecordService {

    @Autowired
    WorkoutRecordRepo workoutRecordRepo;

    public WorkoutRecord saveWorkoutRecord(WorkoutRecord workoutRecord){
        return workoutRecordRepo.save(workoutRecord); // ide errors
    } 

    public List<WorkoutRecord> getAllWorkoutRecord(){
        return workoutRecordRepo.findAll();
    }

    public  Optional<WorkoutRecord> findByWorkoutRecordId(@Nonnull Long id){
        return workoutRecordRepo.findWorkoutRecordById(id);
    }
    public List<WorkoutRecord> findByWorkoutRecordNames(String name){
        return workoutRecordRepo.findWorkoutRecordsbyworkoutName(name);
    }
    public void deleteWorkoutRecord(WorkoutRecord workoutRecord){
        workoutRecordRepo.delete(workoutRecord); //ide errors could be due to 1 to many relationship
    }

    


}

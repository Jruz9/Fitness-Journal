package com.example.fitness_journal_backend.Controllers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import com.example.fitness_journal_backend.Entities.WorkoutRecord;
import com.example.fitness_journal_backend.Services.WorkoutRecordService;

@RestController
@RequestMapping("/api/v1")
public class WorkoutRecordController {

    @Autowired
    private WorkoutRecordService wrs;


    private static final String WORKOUT_RECORD_NOT_FOUND="Could not find the workout records with the ID given";

    private final Logger log= LoggerFactory.getLogger(WorkoutRecordController.class);
    
    // get all exercise records

    @GetMapping("/records")
    public List<WorkoutRecord> displayAllExerciseRecords(){
        return wrs.getAllWorkoutRecord();
    }

    // create new record

    @PostMapping("/records")
    public WorkoutRecord createWorkoutRecord(@Validated @RequestBody WorkoutRecord workoutRecord){
        log.info("Request to create a new workout record : {}",workoutRecord);
        return wrs.saveWorkoutRecord(workoutRecord);
    }


    @PutMapping("/records/{id}")
    public WorkoutRecord updateWorkoutRecord(@Validated @PathVariable("id") Long workoutRecordId , @RequestBody WorkoutRecord updatedWorkoutRecord){
        log.info("Request to change the specified workoutRecord {}", updatedWorkoutRecord);

        final WorkoutRecord oldRecord= wrs.findByWorkoutRecordId(workoutRecordId)
        .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format(WORKOUT_RECORD_NOT_FOUND, workoutRecordId))
        );

        oldRecord.setWorkoutName(updatedWorkoutRecord.getWorkoutName());
        oldRecord.setWorkoutDate(updatedWorkoutRecord.getWorkoutDate());
        oldRecord.setWorkoutData(updatedWorkoutRecord.getWorkoutData());

        return wrs.saveWorkoutRecord(oldRecord);
    }

    @DeleteMapping("/records/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteWorkoutRecord(@PathVariable("id") Long workoutRecordId){
        log.info("Request to delete workout Record : {}",workoutRecordId);
        Optional<WorkoutRecord> deleteRecord=wrs.findByWorkoutRecordId(workoutRecordId);
        if(!deleteRecord.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(String.format(WORKOUT_RECORD_NOT_FOUND, workoutRecordId));
        }
        wrs.deleteWorkoutRecord(deleteRecord.get());
        return ResponseEntity.status(HttpStatus.OK).body("Workout Record deletion was sucessful");
    }



}

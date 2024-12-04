package com.example.fitness_journal_backend.Controllers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.example.fitness_journal_backend.Entities.WorkoutRecord;
import com.example.fitness_journal_backend.Services.WorkoutRecordService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/v1")
public class WorkoutRecordController {

    @Autowired
    private WorkoutRecordService wrs;


    private static final String WORKOUT_RECORD_NOT_FOUND="Could not find the workout records with the ID given";

    private final Logger log= LoggerFactory.getLogger(WorkoutRecordController.class);
    
    // get all exercise records

    @GetMapping("/records")
    public List<WorkoutRecord> getAllWorkoutRecords(){
        return wrs.getAllWorkoutRecord();
    }

    @GetMapping("/records/{id}")
    public WorkoutRecord getOneWorkoutRecord(@Validated @PathVariable("id") Long id){
        try{
            WorkoutRecord record= wrs.getOneWorkoutRecord(id);
            return record;
        }
        catch(EntityNotFoundException e){
            throw new EntityNotFoundException("Record with this id was not found");
        }
        
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
        if(deleteRecord.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(String.format(WORKOUT_RECORD_NOT_FOUND, workoutRecordId));
        }
        wrs.deleteWorkoutRecord(deleteRecord.get());
        return ResponseEntity.status(HttpStatus.OK).body("Workout Record deletion was successful");
    }

    @GetMapping("/recordRange")
    public List<WorkoutRecord> getWorkoutRecordFromDate(LocalDate startDate){
        List<WorkoutRecord> workoutRecordsList= wrs.getAllWorkoutRecordsFromLocalDate(startDate);
        if (workoutRecordsList.get(0).getWorkoutDate() != startDate) {
            throw new  EntityNotFoundException("WorkoutRecord with this date does not exist or has not been found in the database.");
            }
            return workoutRecordsList;

        }
}

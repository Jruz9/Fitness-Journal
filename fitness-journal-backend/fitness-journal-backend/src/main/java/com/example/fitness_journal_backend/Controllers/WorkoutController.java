package com.example.fitness_journal_backend.Controllers;

import java.util.List;
import java.util.Optional;

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

import com.example.fitness_journal_backend.Entities.Workout;
import com.example.fitness_journal_backend.Entities.WorkoutRecord;
import com.example.fitness_journal_backend.Services.WorkoutRecordService;
import com.example.fitness_journal_backend.Services.WorkoutService;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api/v1/") //Change later
public class WorkoutController {
    
    @Autowired
    private WorkoutService workoutService;

    @Autowired
    private WorkoutRecordService workoutRecordService;

    private static final String WORKOUT_DATA_NOT_FOUND= "Could not find the workout associated with the id";

    private final Logger log= LoggerFactory.getLogger(WorkoutController.class);

    // Get all workouts on database

    @GetMapping("/workouts")
    public List<Workout> displayAllWorkout(){
        return workoutService.getAllWorkout();
    }

    //Create new Workout and sets the workout to be a list of workouts assciatued with the workout Record.
    @PostMapping("/workoutRecord/{workoutRecordId}/workout")
    public Workout createWorkoutData(@Validated @RequestBody Workout workout,@PathVariable Long workoutRecordId){
        log.info("Request to create new workout data: {}", workout);
        WorkoutRecord workoutRecord= workoutRecordService.findByWorkoutRecordId(workoutRecordId).orElseThrow(() ->new RuntimeException("WorkoutRecord Not Found"));
        workoutRecord.setWorkoutData(List.of(workout));
        return workoutService.saveWorkout(workout);
    }

//Update a single workout using id and single workout information
    @PutMapping("/workouts/{id}")
    public Workout updateWorkoutData(@Validated @PathVariable("id") Long workoutId,Workout oldWorkoutData){
        log.info("Request to update the specified workout data");
        final Workout currentWorkout= workoutService.findById(workoutId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,String.format(WORKOUT_DATA_NOT_FOUND,workoutId)));
        currentWorkout.setSessions(oldWorkoutData.getSessions());
        currentWorkout.setRep(oldWorkoutData.getRep());
        currentWorkout.setDuration(oldWorkoutData.getDuration());
        currentWorkout.setWeight(oldWorkoutData.getWeight());
        return workoutService.saveWorkout(currentWorkout);
    }

    //depending on requirements changing,multi delete could be good addition.
    @DeleteMapping("/workouts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteWorkout(@PathVariable("id") Long workoutId){
        log.info("Request to delete a workout");
        Optional<Workout> deleteWorkouts= workoutService.findById(workoutId);
        if(deleteWorkouts.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format(WORKOUT_DATA_NOT_FOUND,workoutId));
        }
        workoutService.deleteWorkoutFromWorkoutRecord(deleteWorkouts.get());
        return ResponseEntity.status(HttpStatus.OK).body("Workout deletion was successful");
    }


    @GetMapping("/WorkoutRecord/{workoutRecordId}/workout")
    public List<Workout> getWorkout(@PathVariable Long workoutRecordId){
        WorkoutRecord workout= workoutRecordService.findByWorkoutRecordId(workoutRecordId).orElseThrow
        (() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Workout Record is not found."));
        
        return workout.getWorkoutData();
    }
}

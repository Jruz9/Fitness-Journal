package com.example.fitness_journal_backend.Entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate; //changed from date since old date is has issues for more current systems. 
import java.util.List;

/**
 * A Object that stores the name, time and workouts rep and sets of the single exercise.
 * Example:
 * [arm curls,12:00 utc,{[id=0,set=1,rep=10,weight=20lb,duration=0.00],[id=1,set=2,rep=8,weight=25lb,duration=0.00.....]},]
 */
@Entity
public class WorkoutRecord {

    @Id
    @Nullable
    @GeneratedValue
    private Long id;
    @DateTimeFormat(pattern = "dd-MMM-YYYY")
    @Column
    private LocalDate workoutDate; 
    @Column(nullable = false)
    private String workoutName;
    //A list of workouts ex: [[][][]]

    //Nullary
    public WorkoutRecord(){
    }

    /**
     * Creates the Workout record object.
     * @param id: The id tag of the workout record
     * @param workoutDate: The date when the workout record was created
     */
    public WorkoutRecord(Long id, LocalDate workoutDate, String workoutName) {
        this.id = id;
        this.workoutDate = workoutDate;
        this.workoutName = workoutName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public LocalDate getWorkoutDate() {
        return workoutDate;
    }

    public void setWorkoutDate(LocalDate workoutDate) {
        this.workoutDate = workoutDate;
    }


    // TODO: Create the foreign key connection with workout 
}

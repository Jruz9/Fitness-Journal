package com.example.fitness_journal_backend.Entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
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
    @DateTimeFormat
    @Column
    private Date time;
    @Column(nullable = false)
    private String workoutName;
    //A list of workouts ex: [[][][]]
    @OneToMany(mappedBy = "workout")
    private List<Workout> workoutRecord;

    //Nullary
    public WorkoutRecord(){
    }

    /**
     * Creates the Workout record object.
     * @param id: The id tag of the workout record
     * @param time: The time when the workout record was made
     * @param workoutRecord: A list of exercises completed.
     */
    public WorkoutRecord(Long id, Date time, String workoutName, List<Workout> workoutRecord) {
        this.id = id;
        this.time = time;
        this.workoutName = workoutName;
        this.workoutRecord = workoutRecord;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public List<Workout> getWorkoutRecord() {
        return workoutRecord;
    }

    public void setWorkoutRecord(List<Workout> workoutRecord) {
        this.workoutRecord = workoutRecord;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }


}

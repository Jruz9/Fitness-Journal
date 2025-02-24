package com.example.fitness_journal_backend.Entities;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate; //changed from date since old date is having issues for more current systems.
import java.util.List;

/**
 * A Object that stores the name, time and workouts rep and sets of the single exercise.
 * Example:
 * [arm curls,12:00 utc,{[id=0,set=1,rep=10,weight=20lb,duration=0.00],[id=1,set=2,rep=8,weight=25lb,duration=0.00.....]},]
 */
@Entity
@Table(name = "workoutrecord")
public class WorkoutRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workoutrecordid")
    private Long id;
    @Column(name = "workoutname")
    private String workoutName;
    @DateTimeFormat(pattern = "YYYY-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    @Column(name = "workoutdate")
    private LocalDate workoutDate;
    //A list of workouts ex: [[][][]]
    //removed cascade https://stackoverflow.com/questions/13370221/persistentobjectexception-detached-entity-passed-to-persist-thrown-by-jpa-and-h
    @OneToMany(mappedBy = "workoutRecord",cascade = CascadeType.MERGE)
    private List<Workout> workoutData;

    //Nullary
    public WorkoutRecord(){
    }

    /**
     * Creates the Workout record object.
     * @param id: The id tag of the workout record
     * @param workoutDate: The date when the workout record was created
     * @param  workoutName: The name of the workout record
     * @param workoutData: The array of workouts associated with the workout Record
     *
     */
    public WorkoutRecord(Long id, LocalDate workoutDate, String workoutName,List<Workout> workoutData) {
        this.id = id;
        this.workoutDate = workoutDate;
        this.workoutName = workoutName;
        this.workoutData=workoutData;
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
    public List<Workout> getWorkoutData() {
        return workoutData;
    }

    public void setWorkoutData(List<Workout> workoutData) {
        this.workoutData = workoutData;
    }
}

package com.example.fitness_journal_backend.Entities;

import java.time.LocalDate;

import jakarta.persistence.*;

/**
 * Creates the workout object.
 * It allows user to input their workout sets,reps,weight,and duration per exercise.
 */

@Entity
//@Table(name= "WORKOUT")
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workId;
    @Column(name = "sessions")
    private int sessions;
    @Column(name = "reps")
    private int rep;
    @Column(name = "weight")
    private double weight; // uses pound in the backend :) ;
    @Column(name = "duration")
    private double duration;

    @Column(name = "createdworkoutdate")
    private LocalDate createdWorkoutTime;

    @ManyToOne(fetch = FetchType.LAZY,optional = true,cascade = CascadeType.MERGE)
    @JoinColumn(name="workoutId")
    private WorkoutRecord workoutRecord;
    /**
     * A nullary empty workout object.
     */
    public Workout() {
    }

    /**
     * Creates the workout object
     * @param duration: The time of the exercise
     * @param weight: The weight in LB for the exercise
     * @param rep: The repetition of the exercise
     * @param sessions: The total sessions for the exercise
     * @param createdWorkoutTime: the Date that the workout was created
     * @param workoutRecord: The Workout Record that is associated with the workouts
     */
    public Workout(double duration, double weight, int rep, int sessions, Long id, LocalDate createdWorkoutTime, WorkoutRecord workoutRecord) {
        this.duration = duration;
        this.weight = weight;
        this.rep = rep;
        this.sessions = sessions;
        this.workId = id;
        this.createdWorkoutTime = createdWorkoutTime;
        this.workoutRecord=workoutRecord;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getRep() {
        return rep;
    }

    public void setRep(int rep) {
        this.rep = rep;
    }

    public int getSessions() {
        return sessions;
    }

    public void setSessions(int sessions) {
        this.sessions = sessions;
    }

    public Long getId() {
        return workId;
    }

    public void setId(Long id) {
        this.workId = id;
    }

    
    // Here is a resource to help: https://stackoverflow.com/questions/4099237/how-to-map-a-2-d-matrix-in-java-to-hibernate-jpa
    // https://levelup.gitconnected.com/how-to-deal-with-nested-entities-in-spring-controller-8bb404eae3a2
    // https://stackoverflow.com/questions/25415738/how-to-persist-arraylist-within-spring-entity-class (for entity in 1d)

    public WorkoutRecord getWorkoutRecord() {
        return workoutRecord;
    }

    public void setWorkoutRecord(WorkoutRecord workoutRecord) {
        this.workoutRecord = workoutRecord;
    }

    public LocalDate getCreatedWorkoutTime() {
        return createdWorkoutTime;
    }

    public void setCreatedWorkoutTime(LocalDate createdWorkoutTime) {
        this.createdWorkoutTime = createdWorkoutTime;
    }
}

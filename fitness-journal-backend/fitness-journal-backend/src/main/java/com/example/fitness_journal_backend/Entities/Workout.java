package com.example.fitness_journal_backend.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.sql.Time; // if it errors out use the util.date package

@Entity
@Table(name= "workout")
public class Workout {
    private int id;
    private int set;
    private int rep;
    private double weight; // uses pound in the backend :) ;
    private Time time;
    //Nullary workout method
    public Workout() {
    }

    public Workout(Time time, double weight, int rep, int set, int id) {
        this.time = time;
        this.weight = weight;
        this.rep = rep;
        this.set = set;
        this.id = id;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
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

    public int getSet() {
        return set;
    }

    public void setSet(int set) {
        this.set = set;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    //TODO Create a way for nest array for the entitiy.
    // Here is a resource to help: https://stackoverflow.com/questions/4099237/how-to-map-a-2-d-matrix-in-java-to-hibernate-jpa
    // https://levelup.gitconnected.com/how-to-deal-with-nested-entities-in-spring-controller-8bb404eae3a2
    // https://stackoverflow.com/questions/25415738/how-to-persist-arraylist-within-spring-entity-class (for entitly in 1d)

}

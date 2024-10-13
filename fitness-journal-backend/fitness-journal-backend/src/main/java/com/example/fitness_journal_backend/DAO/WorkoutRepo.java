package com.example.fitness_journal_backend.DAO;

import com.example.fitness_journal_backend.Entities.Workout;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.example.fitness_journal_backend.Entities.Workout;
import java.util.List;
import java.util.Optional;

/**
 *
 */
public interface WorkoutRepo extends PagingAndSortingRepository<Workout,Long> {
    
    public List<Workout> findAllByWorkouts();

    public Optional<Workout> findByWorkoutId(@Param("id")long workoutId);
}
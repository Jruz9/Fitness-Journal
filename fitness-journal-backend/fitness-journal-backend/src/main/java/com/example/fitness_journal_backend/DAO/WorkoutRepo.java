package com.example.fitness_journal_backend.DAO;

import com.example.fitness_journal_backend.Entities.Workout;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 *
 */
public interface WorkoutRepo extends PagingAndSortingRepository<Workout,Long> {

    List<Workout> findAll(Long workoutID);
}
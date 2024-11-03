package com.example.fitness_journal_backend.DAO;
import com.example.fitness_journal_backend.Entities.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

/**
 *
 */
public interface WorkoutRepo extends JpaRepository<Workout,Long> {
    @Override
    public List<Workout> findAll();

    public Optional<Workout> findById(@Param("id")long workoutId);
}
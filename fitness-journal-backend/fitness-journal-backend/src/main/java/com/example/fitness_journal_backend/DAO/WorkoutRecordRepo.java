package com.example.fitness_journal_backend.DAO;

import com.example.fitness_journal_backend.Entities.WorkoutRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface WorkoutRecordRepo extends JpaRepository<WorkoutRecord,Long> {

    public Optional<WorkoutRecord> findWorkoutRecordById(Long id);

    List<WorkoutRecord> findWorkoutRecordByworkoutDate(LocalDate time);

    List<WorkoutRecord> findWorkoutRecordByworkoutName(String workoutName);

    @Override
    public List<WorkoutRecord> findAll();
    


}

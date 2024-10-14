package com.example.fitness_journal_backend.DAO;

import com.example.fitness_journal_backend.Entities.WorkoutRecord;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;



public interface WorkoutRecordRepo extends PagingAndSortingRepository<WorkoutRecord,Long> {

    public Optional<WorkoutRecord> findWorkoutRecordById(Long id);

    List<WorkoutRecord> findWorkoutRecordByDate(LocalDate time);

    List<WorkoutRecord> findWorkoutRecordsbyworkoutName(String workoutName);

    @Override
    public List<WorkoutRecord> findAll();

}

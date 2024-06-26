package com.example.fitness_journal_backend.DAO;

import com.example.fitness_journal_backend.Entities.WorkoutRecord;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;


public interface WorkoutRecordRepo extends PagingAndSortingRepository<WorkoutRecord,Long> {

    List<WorkoutRecord> findWorkoutRecordById(Long id);

    List<WorkoutRecord> findWorkoutRecordByDate(Date time);

}

package com.example.fitness_journal_backend.Controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public final class WorkoutRecordNotFoundException extends RuntimeException{


    public WorkoutRecordNotFoundException(){
        super();
    }
}

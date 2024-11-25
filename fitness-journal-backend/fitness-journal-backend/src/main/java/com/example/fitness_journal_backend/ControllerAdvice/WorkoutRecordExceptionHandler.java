package com.example.fitness_journal_backend.ControllerAdvice;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class WorkoutRecordExceptionHandler{
    public static final String DEFUALT_ERROR= "Error";
    @ExceptionHandler(value= Exception.class)
    public ModelAndView defaultErrorHandle(HttpServletRequest req, Exception e) throws Exception{
//        https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc#header
        if (AnnotationUtils.findAnnotation
                (e.getClass(), ResponseStatus.class) != null)
            throw e;

        // Otherwise setup and send the user to a default error-view.
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName(DEFUALT_ERROR);
        return mav;
    }
}
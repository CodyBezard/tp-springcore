package fr.diginamic.hello.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionTest {
    @ExceptionHandler
    protected ResponseEntity<String> traiterErreurs(FunctionalException ex){
      System.out.println(ex.getMessage());
      return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler
    protected ResponseEntity<String> traiterVilleNotFound(VilleNotFound ex){
        System.out.println(ex.getMessage());
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}

package T3.booking_system.aop;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handle1 (RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("RuntimeExcept: " + exception.getMessage());
    }

}

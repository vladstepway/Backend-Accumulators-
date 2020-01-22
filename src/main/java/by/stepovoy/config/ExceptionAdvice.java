package by.stepovoy.config;

import by.stepovoy.model.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(RuntimeException.class)
    public ApiResponse<Object> handleNotFoundException(RuntimeException ex) {
        return new ApiResponse<>(400, "Bad request", null);
    }

}

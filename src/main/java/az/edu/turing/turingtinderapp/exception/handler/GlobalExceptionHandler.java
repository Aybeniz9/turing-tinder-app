package az.edu.turing.turingtinderapp.exception.handler;

import az.edu.turing.turingtinderapp.exception.ExistingUserException;
import az.edu.turing.turingtinderapp.exception.UserNotFoundException;
import az.edu.turing.turingtinderapp.exception.WrongPasswordException;
import az.edu.turing.turingtinderapp.model.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ErrorResponse handleUserNotFoundException(UserNotFoundException ex) {
        return ErrorResponse.builder().errorCode(HttpStatus.NOT_FOUND.value()).errorMessage(ex.getMessage()).build();
    }

    @ExceptionHandler(ExistingUserException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public @ResponseBody ErrorResponse handleExistingUserException(ExistingUserException ex) {
        return ErrorResponse.builder().errorCode(HttpStatus.CONFLICT.value()).errorMessage(ex.getMessage()).build();
    }

    @ExceptionHandler(WrongPasswordException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public @ResponseBody ErrorResponse handleWrongPasswordException(WrongPasswordException ex) {
        return ErrorResponse.builder().errorCode(HttpStatus.BAD_REQUEST.value()).errorMessage(ex.getMessage()).build();
    }
}

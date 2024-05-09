package ecom.Helper.Handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import ecom.DTO.ErrorDTO;
import ecom.Helper.Handler.Exceptions.BadRequestException;
import ecom.Helper.Handler.Exceptions.ConflictException;
import ecom.Helper.Handler.Exceptions.NotFoundException;
import ecom.Helper.Handler.Exceptions.UnauthorizedException;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ResponseExceptionHandler  {

  
  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorDTO handlerNotFoundException(NotFoundException ex, WebRequest req) {
    return new ErrorDTO(HttpStatus.NOT_FOUND,ex.getMessage());
  }

  @ExceptionHandler(ConflictException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public ErrorDTO handlerConflictException(ConflictException ex, WebRequest req) {
    return new ErrorDTO(HttpStatus.CONFLICT,ex.getMessage());
  }

  @ExceptionHandler(BadRequestException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorDTO handlerBadRequestException(BadRequestException ex, WebRequest req) {
    return new ErrorDTO(HttpStatus.BAD_REQUEST,ex.getMessage());
  }

  @ExceptionHandler(UnauthorizedException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ErrorDTO handlerUnauthorizedException(UnauthorizedException ex, WebRequest req) {
    return new ErrorDTO(HttpStatus.UNAUTHORIZED,ex.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest req) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        Map<String, Object> objectBody = new LinkedHashMap<>();
        objectBody.put("Current Timestamp", new Date());
        objectBody.put("Status", httpStatus.value());

        List<String> exceptionError = ex.getBindingResult()
                                        .getFieldErrors()
                                        .stream()
                                        .map(fieldError -> fieldError.getDefaultMessage())
                                        .collect(Collectors.toList());
        objectBody.put("Errors", exceptionError);

        return new ResponseEntity<>(objectBody, httpStatus);
    }
  
}

package BusAdminServices.BusAdmin.Exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandling extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> alreadyExists(Exception exe){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exe.getMessage());
        errorResponse.setDate(new Date());
        errorResponse.setStatusCode(HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(errorResponse,HttpStatus.CONFLICT);
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String,String> errors= new HashMap<>();
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        allErrors.forEach((error)->{
            String feildname = ((FieldError)error).getField();
            String feildMessage = error.getDefaultMessage();
            errors.put(feildname,feildMessage);
        });
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataNotFounException.class)
    public ResponseEntity<ErrorResponse> dataAlreadyFound(Exception exe){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exe.getMessage());
        errorResponse.setDate(new Date());
        errorResponse.setStatusCode(HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(errorResponse,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DataMissMatchException.class)
    public ResponseEntity<ErrorResponse> dataMissMatchException(Exception exe){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exe.getMessage());
        errorResponse.setDate(new Date());
        errorResponse.setStatusCode(HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(errorResponse,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> Exception(Exception exe){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exe.getMessage());
        errorResponse.setDate(new Date());
        errorResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }



}

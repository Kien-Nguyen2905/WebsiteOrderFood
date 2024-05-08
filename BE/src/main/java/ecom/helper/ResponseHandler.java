package ecom.Helper;

import java.util.HashMap;

import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

    public static ResponseEntity<Object> success(
            String status,
            String message,
            HttpStatus httpStatus,
            Object data) {

        Map<String, Object> response = new HashMap<>();

        
        response.put("status", status);
        response.put("message", message);
        response.put("httpStatus", httpStatus);
        response.put("data", data);
        return new ResponseEntity<>(response,httpStatus);
    }

    // public static ResponseEntity<Object> fail(
    //     String message, HttpStatus httpStatus, Object data) {

    //     Map<String, Object> response = new HashMap<>();

    //     response.put("message", message);
    //     response.put("httpStatus", httpStatus);
    //     response.put("data", data);
    //     return new ResponseEntity<>(response,httpStatus);
    // }

}

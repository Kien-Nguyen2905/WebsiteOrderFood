package ecom.Helper.Handler;

import java.util.HashMap;

import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

    public static ResponseEntity<Object> success(
        
            String message,
            HttpStatus httpStatus,
            Object data) {

        Map<String, Object> response = new HashMap<>();

        
        response.put("message", message);
        response.put("httpStatus", httpStatus);
        response.put("data", data);

        return new ResponseEntity<>(response,httpStatus);
    }

}

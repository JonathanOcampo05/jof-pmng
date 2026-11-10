package mx.edu.utez.myservice.kernel;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data

public class ApiResponse {

    private String message;
    private Object data;
    private Boolean error;
    private HttpStatus status;

    public ApiResponse(Object data, String message, Boolean error, HttpStatus status) {
        this.data = data;
        this.message = message;
        this.error = error;
        this.status = status;
    }

    public ApiResponse(String message, Object data, HttpStatus status) {
        this.message = message;
        this.data = data;
        this.status = status;
    }

    public ApiResponse() {

    }
}

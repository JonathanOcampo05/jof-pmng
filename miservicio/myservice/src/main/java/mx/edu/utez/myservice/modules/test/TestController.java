package mx.edu.utez.myservice.modules.test;

import mx.edu.utez.myservice.kernel.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@CrossOrigin(origins = "*")
public class TestController {

    @GetMapping("")
    public ResponseEntity<ApiResponse> testAPI(){
        ApiResponse body = new ApiResponse("Api en funcionamiento", null, HttpStatus.OK);

        return new ResponseEntity<>(body, body.getStatus());
    }
}

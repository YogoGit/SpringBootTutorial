package edu.carroll.cs389;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/")
    public ResponseEntity<String> index() {
        return new ResponseEntity<>("Hello from Spring Boot!", HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<String> user(@RequestParam("name")String username) {
        return new ResponseEntity<>("Hello " + username + "!", HttpStatus.OK);
    }
}

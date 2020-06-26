package hanane.sid.MockitoRestSpring;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloResource {

    @GetMapping
    public String helloWorld(){
        return "hello world!";
    }

    @GetMapping(value = "/json",produces = MediaType.APPLICATION_JSON_VALUE)
    public Hello JsonH(){
        return new Hello("greeting","hello hanane");
    }
}

package SJTU.SE.courseAffair;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class helloTest {
    @RequestMapping("/hello")
    String hello() {
        System.out.println("hello");
        return "hello";
    }
}

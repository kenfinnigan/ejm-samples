package ejm.chapter1.spring.controller;

import ejm.chapter1.spring.services.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ken Finnigan
 */
@RestController
@RequestMapping("/hello")
public class HelloRestController {

    @Autowired
    HelloService helloService;

    @RequestMapping(value = "/{name}", method = RequestMethod.GET, produces = "text/plain")
    public ResponseEntity<String> sayHello(@PathVariable("name") String name) {
        return new ResponseEntity<String>(helloService.sayHello(name), HttpStatus.OK);
    }
}

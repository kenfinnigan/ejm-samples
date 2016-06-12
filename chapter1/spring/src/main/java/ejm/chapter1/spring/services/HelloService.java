package ejm.chapter1.spring.services;

import org.springframework.stereotype.Service;

/**
 * @author Ken Finnigan
 */
@Service
public class HelloService {

    public String sayHello(String name) {
        return "Hello " + name;
    }
}

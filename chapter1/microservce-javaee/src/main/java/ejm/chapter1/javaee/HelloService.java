package ejm.chapter1.javaee;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author Ken Finnigan
 */
@ApplicationScoped
public class HelloService {

    public String sayHello(String name) {
        return "Hello " + name;
    }
}

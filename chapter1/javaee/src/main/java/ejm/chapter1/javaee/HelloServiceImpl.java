package ejm.chapter1.javaee;

import javax.enterprise.context.RequestScoped;

/**
 * @author Ken Finnigan
 */
@RequestScoped
public class HelloServiceImpl implements HelloService {

    public String sayHello(String name) {
        return "Hello " + name;
    }
}

package ejm.chapter1.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ken Finnigan
 */
@Configuration
@ComponentScan("ejm.chapter1.spring.services")
public class AppConfig {
}

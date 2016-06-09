package ejm.chapter1.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Ken Finnigan
 */
@Configuration
@EnableWebMvc
@ComponentScan("ejm.chapter1.spring.controller")
public class WebConfig {
}

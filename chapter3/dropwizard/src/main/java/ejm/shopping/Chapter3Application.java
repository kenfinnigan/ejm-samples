package ejm.shopping;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class Chapter3Application extends Application<Chapter3Configuration> {

    public static void main(final String[] args) throws Exception {
        new Chapter3Application().run(args);
    }

    @Override
    public String getName() {
        return "chapter3";
    }

    @Override
    public void initialize(final Bootstrap<Chapter3Configuration> bootstrap) {
    }

    @Override
    public void run(final Chapter3Configuration configuration,
                    final Environment environment) {
        final CartController resource = new CartController();
        environment.jersey().register(resource);
    }

}

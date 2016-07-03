package ejm.chapter2.employee;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.topology.webapp.TopologyProperties;
import org.wildfly.swarm.topology.webapp.TopologyWebAppFraction;
import org.wildfly.swarm.undertow.WARArchive;

/**
 * @author Ken Finnigan
 */
public class Main {
    public static void main(String... args) throws Exception {
        Swarm swarm = new Swarm();

        System.setProperty(TopologyProperties.CONTEXT_PATH, "/topology-webapp");
        TopologyWebAppFraction topologyWebAppFraction = new TopologyWebAppFraction();
        swarm.fraction(topologyWebAppFraction);

        swarm.start();

        WARArchive war = ShrinkWrap.create(WARArchive.class);
        war.staticContent();
        war.addAllDependencies();
        war.addModule("org.wildfly.swarm.container");
        swarm.deploy(war);
    }
}

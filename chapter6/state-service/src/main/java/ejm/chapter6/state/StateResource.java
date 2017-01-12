package ejm.chapter6.state;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * @author Ken Finnigan
 */
@Path("/")
public class StateResource {

    private Map<String, String> states = new HashMap<>();

    private static AtomicInteger counter = new AtomicInteger(1);

    public StateResource() {
        populateStates();
    }

    @GET
    @Path("/")
    public String getStateName(@QueryParam("code") String stateCode) throws Exception {
        int requestNum = counter.get();
        System.out.println("Processing request number " + requestNum + " for code: " + stateCode);
        counter.incrementAndGet();

        if (requestNum % 10 == 0) {
            throw new RuntimeException("Simulated Failure!");
        }

        if (requestNum % 2 == 0) {
            Thread.sleep(10000);
        }

        return states.get(stateCode) + " [Request#: " + requestNum + "]";
    }

    private void populateStates() {
        states.put("AK", "Alaska");
        states.put("AL", "Alabama");
        states.put("AR", "Arkansas");
        states.put("AZ", "Arizona");
        states.put("CA", "California");
        states.put("CO", "Colorado");
        states.put("CT", "Connecticut");
        states.put("DE", "Delaware");
        states.put("FL", "Florida");
        states.put("GA", "Georgia");
        states.put("HI", "Hawaii");
        states.put("IA", "Iowa");
        states.put("ID", "Idaho");
        states.put("IL", "Illinois");
        states.put("IN", "Indiana");
        states.put("KS", "Kansas");
        states.put("KY", "Kentucky");
        states.put("LA", "Louisiana");
        states.put("MA", "Massachusetts");
        states.put("MD", "Maryland");
        states.put("ME", "Maine");
        states.put("MI", "Michigan");
        states.put("MN", "Minnesota");
        states.put("MO", "Missouri");
        states.put("MS", "Mississippi");
        states.put("MT", "Montana");
        states.put("NC", "North Carolina");
        states.put("ND", "North Dakota");
        states.put("NE", "Nebraska");
        states.put("NH", "New Hampshire");
        states.put("NJ", "New Jersey");
        states.put("NM", "New Mexico");
        states.put("NV", "Nevada");
        states.put("NY", "New York");
        states.put("OH", "Ohio");
        states.put("OK", "Oklahoma");
        states.put("OR", "Oregon");
        states.put("PA", "Pennsylvania");
        states.put("RI", "Rhode Island");
        states.put("SC", "South Carolina");
        states.put("SD", "South Dakota");
        states.put("TN", "Tennessee");
        states.put("TX", "Texas");
        states.put("UT", "Utah");
        states.put("VA", "Virginia");
        states.put("VT", "Vermont");
        states.put("WA", "Washington");
        states.put("WI", "Wisconsin");
        states.put("WV", "West Virginia");
        states.put("WY", "Wyoming");
    }
}

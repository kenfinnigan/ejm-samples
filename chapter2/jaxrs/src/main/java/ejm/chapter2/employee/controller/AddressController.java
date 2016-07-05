package ejm.chapter2.employee.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ejm.chapter2.employee.model.Address;

/**
 * @author Ken Finnigan
 */
@Path("/address")
public class AddressController {
    private int idCounter = 1;

    private Map<Integer, Address> addresses = new HashMap<>();

    public AddressController() {
        Address address = new Address(idCounter++, "100 East Davie Street", null, "Raleigh", "NC");
        addresses.put(address.getId(), address);

        address = new Address(idCounter++, "314 Littleton Road", null, "Westford", "MA");
        addresses.put(address.getId(), address);

        address = new Address(idCounter++, "600 My Street", null, "Raleigh", "NC");
        addresses.put(address.getId(), address);

        address = new Address(idCounter++, "255 My Crescent", null, "Westford", "MA");
        addresses.put(address.getId(), address);
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Address> all() throws Exception {
        return addresses.values().stream().collect(Collectors.toList());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Address createAddress(Address address) throws Exception {
        address.setId(idCounter++);
        addresses.put(address.getId(), address);
        return address;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{addressId}")
    public Address getAddress(@PathParam("addressId") Integer addressId) {
        return addresses.get(addressId);
    }

    @DELETE
    @Path("/{addressId}")
    public void removeAddress(@PathParam("addressId") Integer addressId) throws Exception {
        addresses.remove(addressId);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{addressId}")
    public Address updateAddress(@PathParam("addressId") Integer addressId, Address address) throws Exception {
        addresses.put(address.getId(), address);
        return this.getAddress(address.getId());
    }
}

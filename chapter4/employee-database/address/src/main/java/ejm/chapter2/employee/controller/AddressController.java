package ejm.chapter2.employee.controller;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
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
import ejm.chapter2.employee.service.AddressService;

/**
 * @author Ken Finnigan
 */
@ApplicationScoped
@Path("/address")
public class AddressController {

    @Inject
    AddressService service;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Address> all() throws Exception {
        return service.getAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Address createAddress(Address address) throws Exception {
        return service.create(address);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{addressId}")
    public Address getAddress(@PathParam("addressId") Integer addressId) throws Exception {
        return service.retrieve(addressId);
    }

    @DELETE
    @Path("/{addressId}")
    public void removeAddress(@PathParam("addressId") Integer addressId) throws Exception {
        service.delete(addressId);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{addressId}")
    public Address updateAddress(@PathParam("addressId") Integer addressId, Address address) throws Exception {
        return service.save(address);
    }
}

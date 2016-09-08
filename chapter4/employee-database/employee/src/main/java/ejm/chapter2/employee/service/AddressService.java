package ejm.chapter2.employee.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ejm.chapter2.employee.model.Address;

/**
 * @author Ken Finnigan
 */
@Path("/address")
public interface AddressService {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{addressId}")
    Address getAddress(@PathParam("addressId") Integer addressId) throws Exception;
}

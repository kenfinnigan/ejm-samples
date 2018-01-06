package ejm.resteasyclient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Ken Finnigan
 */
@Path("/admin/categorytree")
public interface CategoryService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Category getCategoryTree();
}

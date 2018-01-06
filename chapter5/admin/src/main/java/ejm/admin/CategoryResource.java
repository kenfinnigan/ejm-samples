package ejm.admin;

import java.net.URI;
import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ejm.admin.model.Category;
import ejm.admin.model.CategoryTree;

/**
 * @author Ken Finnigan
 */
@Path("/category")
@ApplicationScoped
public class CategoryResource {

    @PersistenceContext(unitName = "AdminPU")
    private EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Category> all() throws Exception {
        return em.createNamedQuery("Category.findAll", Category.class)
                .getResultList();
    }

    @GET
    @Path("/tree")
    @Produces(MediaType.APPLICATION_JSON)
    public CategoryTree tree() throws Exception {
        return em.find(CategoryTree.class, 1);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(Category category) throws Exception {
        if (category.getId() != null) {
            return Response
                    .status(Response.Status.CONFLICT)
                    .entity("Unable to create Category, id was already set.")
                    .build();
        }

        Category parent;
        if ((parent = category.getParent()) != null && parent.getId() != null) {
            category.setParent(get(parent.getId()));
        }

        try {
            em.persist(category);
            em.flush();
        } catch (ConstraintViolationException cve) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(cve.getMessage())
                    .build();
        } catch (Exception e) {
            return Response
                    .serverError()
                    .entity(e.getMessage())
                    .build();
        }
        return Response
                .created(new URI("category/" + category.getId().toString()))
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{categoryId}")
    public Category get(@PathParam("categoryId") Integer categoryId) {
        return em.find(Category.class, categoryId);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{categoryId}")
    @Transactional
    public Response remove(@PathParam("categoryId") Integer categoryId) throws Exception {
        try {
            Category entity = em.find(Category.class, categoryId);
            em.remove(entity);
        } catch (Exception e) {
            return Response
                    .serverError()
                    .entity(e.getMessage())
                    .build();
        }

        return Response
                .noContent()
                .build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{categoryId}")
    @Transactional
    public Response update(@PathParam("categoryId") Integer categoryId, Category category) throws Exception {
        try {
            Category entity = em.find(Category.class, categoryId);

            if (null == entity) {
                return Response
                        .status(Response.Status.NOT_FOUND)
                        .entity("Category with id of " + categoryId + " does not exist.")
                        .build();
            }

            Category parent;
            if ((parent = category.getParent()) != null) {
                if (parent.getId() != null && parent.getVersion() == null) {
                    category.setParent(get(parent.getId()));
                }
            }

            em.merge(category);

            return Response
                    .ok(category)
                    .build();
        } catch (Exception e) {
            return Response
                    .serverError()
                    .entity(e.getMessage())
                    .build();
        }
    }
}

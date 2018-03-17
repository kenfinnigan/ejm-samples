package ejm.admin;

import java.net.URI;
import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import ejm.admin.model.Category;
import ejm.admin.model.CategoryTree;
import org.aerogear.kafka.SimpleKafkaProducer;
import org.aerogear.kafka.cdi.annotation.KafkaConfig;
import org.aerogear.kafka.cdi.annotation.Producer;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;

/**
 * @author Ken Finnigan
 */
@Path("/")
@ApplicationScoped
@KafkaConfig(bootstrapServers = "#{KAFKA_SERVICE_HOST}:#{KAFKA_SERVICE_PORT}")
public class CategoryResource {

    @PersistenceContext(unitName = "AdminPU")
    private EntityManager em;

    @Producer
    private SimpleKafkaProducer<Integer, Category> producer;

    @GET
    @Path("/category")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Category> all() throws Exception {
        return em.createNamedQuery("Category.findAll", Category.class)
                .getResultList();
    }

    @GET
    @Path("/categorytree")
    @Produces(MediaType.APPLICATION_JSON)
    public CategoryTree tree() throws Exception {
        return em.find(CategoryTree.class, 1);
    }

    @POST
    @Path("/category")
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

        try {
            em.persist(category);
        } catch (Exception e) {
            return Response
                    .serverError()
                    .entity(e.getMessage())
                    .build();
        }

        producer.send("category_topic", category.getId(), category);

        return Response
                .created(new URI(category.getId().toString()))
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/category/{categoryId}")
    public Category get(@PathParam("categoryId") Integer categoryId) {
        return em.find(Category.class, categoryId);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/category/{categoryId}")
    @Transactional
    public Response remove(@PathParam("categoryId") Integer categoryId, @Context SecurityContext context) throws Exception {
        String username = "";

        if (context.getUserPrincipal() instanceof KeycloakPrincipal) {
            KeycloakPrincipal<KeycloakSecurityContext> kp = (KeycloakPrincipal<KeycloakSecurityContext>) context.getUserPrincipal();

            username = kp.getKeycloakSecurityContext().getToken().getName();
        }

        try {
            Category entity = em.find(Category.class, categoryId);
            em.remove(entity);
            System.out.println(username + " is deleting category with id: " + categoryId);
        } catch (Exception e) {
            return Response
                    .serverError()
                    .entity(e.getMessage())
                    .build();
        }

        producer.send("category_topic", categoryId, null);

        return Response
                .noContent()
                .build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/category/{categoryId}")
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

            em.merge(category);

            producer.send("category_topic", categoryId, category);

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

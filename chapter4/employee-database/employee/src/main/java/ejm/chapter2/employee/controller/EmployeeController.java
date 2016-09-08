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

import ejm.chapter2.employee.service.EmployeeService;
import ejm.chapter2.employee.model.Employee;

/**
 * @author Ken Finnigan
 */
@ApplicationScoped
@Path("/employee")
public class EmployeeController {

    @Inject
    EmployeeService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Employee> allEmployees() throws Exception {
        return service.getAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Employee createEmployee(Employee employee) throws Exception {
        return service.create(employee);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{employeeId}")
    public Employee getEmployee(@PathParam("employeeId") Integer employeeId) throws Exception {
        return service.retrieve(employeeId);
    }

    @DELETE
    @Path("/{employeeId}")
    public void removeEmployee(@PathParam("employeeId") Integer employeeId) throws Exception {
        service.delete(employeeId);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{employeeId}")
    public Employee updateEmployee(@PathParam("employeeId") Integer employeeId, Employee employee) throws Exception {
        return service.save(employee);
    }
}

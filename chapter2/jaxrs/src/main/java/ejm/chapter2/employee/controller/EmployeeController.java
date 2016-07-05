package ejm.chapter2.employee.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import ejm.chapter2.employee.model.Address;
import ejm.chapter2.employee.model.Employee;

/**
 * @author Ken Finnigan
 */
@Path("/employee")
public class EmployeeController {
    private int idCounter = 1;

    Map<Integer, Employee> employees = new HashMap<>();

    @Context
    ResourceContext resourceContext;

    public EmployeeController() {
        Employee employee = new Employee(idCounter++, "Steve", "Bishop", "123-45-6789", new BigDecimal(45678.00), "123-456-7890", new Address(3), null, new Address(1));
        employees.put(employee.getId(), employee);

        employee = new Employee(idCounter++, "Martin", "Smith", "987-65-4321", new BigDecimal(56740.21), "987-654-3210", new Address(4), null, new Address(2));
        employees.put(employee.getId(), employee);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Employee> allEmployees() throws Exception {
        List<Employee> result = new ArrayList<>();

        final AddressController addressController = resourceContext.getResource(AddressController.class);

        employees.values().stream().forEach(e -> {
            e.setHomeAddress(addressController.getAddress(e.getHomeAddress().getId()));
            e.setWorkAddress(addressController.getAddress(e.getWorkAddress().getId()));
            result.add(e);
        });

        return result;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Employee createEmployee(Employee employee) throws Exception {
        employee.setId(idCounter++);
        employees.put(employee.getId(), employee);
        return employee;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{employeeId}")
    public Employee getEmployee(@PathParam("employeeId") Integer employeeId) throws Exception {
        return employees.get(employeeId);
    }

    @DELETE
    @Path("/{employeeId}")
    public void removeEmployee(@PathParam("employeeId") Integer employeeId) throws Exception {
        employees.remove(employeeId);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{employeeId}")
    public Employee updateEmployee(@PathParam("employeeId") Integer employeeId, Employee employee) throws Exception {
        employees.put(employee.getId(), employee);
        return this.getEmployee(employee.getId());
    }
}

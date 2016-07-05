package ejm.chapter2.employee.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import ejm.chapter2.employee.model.Address;
import ejm.chapter2.employee.model.Employee;

/**
 * @author Ken Finnigan
 */
@ApplicationScoped
public class EmployeeService {
    private int idCounter = 1;

    Map<Integer, Employee> employees = new HashMap<>();

    @Inject
    AddressService addressService;

    @PostConstruct
    private void init() {
        Employee employee = new Employee(idCounter++, "Steve", "Bishop", "123-45-6789", new BigDecimal(45678.00), "123-456-7890", new Address(3), null, new Address(1));
        employees.put(employee.getId(), employee);

        employee = new Employee(idCounter++, "Martin", "Smith", "987-65-4321", new BigDecimal(56740.21), "987-654-3210", new Address(4), null, new Address(2));
        employees.put(employee.getId(), employee);
    }

    public List<Employee> getAll() throws Exception {
        List<Employee> result = new ArrayList<>();

        employees.values().stream().forEach(e -> {
            e.setHomeAddress(addressService.retrieve(e.getHomeAddress().getId()));
            e.setWorkAddress(addressService.retrieve(e.getWorkAddress().getId()));
            result.add(e);
        });

        return result;
    }

    public Employee create(Employee employee) throws Exception {
        employee.setId(idCounter++);
        employees.put(employee.getId(), employee);
        return employee;
    }

    public Employee retrieve(Integer employeeId) throws Exception {
        return employees.get(employeeId);
    }

    public void delete(Integer employeeId) throws Exception {
        employees.remove(employeeId);
    }

    public Employee save(Employee employee) throws Exception {
        employees.put(employee.getId(), employee);
        return this.retrieve(employee.getId());
    }
}

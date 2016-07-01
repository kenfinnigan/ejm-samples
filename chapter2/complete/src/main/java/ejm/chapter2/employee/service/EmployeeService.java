package ejm.chapter2.employee.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.PathParam;

import ejm.chapter2.employee.model.Employee;

/**
 * @author Ken Finnigan
 */
@ApplicationScoped
public class EmployeeService {

    @PersistenceContext
    EntityManager entityManager;

    public List<Employee> getAll() throws Exception {
        return entityManager.createNamedQuery("Employee.findAll", Employee.class).getResultList();
    }

    @Transactional
    public Employee create(Employee employee) throws Exception {
        entityManager.persist(employee);
        return employee;
    }

    public Employee retrieve(Integer employeeId) throws Exception {
        Employee employee = entityManager.find(Employee.class, employeeId);
        if (employee == null) {
            throw new EntityNotFoundException("Employee for id " + employeeId + " was not found");
        }
        return employee;
    }

    @Transactional
    public void delete(Integer employeeId) throws Exception {
        Employee employee = entityManager.find(Employee.class, employeeId);

        if (employee != null) {
            entityManager.remove(employee);
        } else {
            throw new EntityNotFoundException("Employee for id " + employeeId + " was not found");
        }
    }

    @Transactional
    public Employee save(Employee employee) throws Exception {
        entityManager.merge(employee);
        return entityManager.find(Employee.class, employee.getId());
    }
}

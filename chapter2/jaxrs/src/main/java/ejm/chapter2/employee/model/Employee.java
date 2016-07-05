package ejm.chapter2.employee.model;

import java.math.BigDecimal;
import java.util.Objects;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Ken Finnigan
 */
@XmlRootElement
public class Employee {

    public Employee(Integer id, String firstname, String lastname, String socialSecurity, BigDecimal salary, String homePhone,
                    Address homeAddress, String workPhone, Address workAddress) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.socialSecurity = socialSecurity;
        this.salary = salary;
        this.homePhone = homePhone;
        this.homeAddress = homeAddress;
        this.workPhone = workPhone;
        this.workAddress = workAddress;
    }

    private Integer id;

    private String firstname;

    private String lastname;

    private String socialSecurity;

    private BigDecimal salary;

    private String homePhone;

    private Address homeAddress;

    private String workPhone;

    private Address workAddress;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public Employee setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public Employee setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getSocialSecurity() {
        return socialSecurity;
    }

    public Employee setSocialSecurity(String socialSecurity) {
        this.socialSecurity = socialSecurity;
        return this;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public Employee setSalary(BigDecimal salary) {
        this.salary = salary;
        return this;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public Employee setHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public Employee setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
        return this;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public Employee setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public Address getWorkAddress() {
        return workAddress;
    }

    public Employee setWorkAddress(Address workAddress) {
        this.workAddress = workAddress;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) &&
                Objects.equals(firstname, employee.firstname) &&
                Objects.equals(lastname, employee.lastname) &&
                Objects.equals(socialSecurity, employee.socialSecurity) &&
                Objects.equals(salary, employee.salary) &&
                Objects.equals(homePhone, employee.homePhone) &&
                Objects.equals(homeAddress, employee.homeAddress) &&
                Objects.equals(workPhone, employee.workPhone) &&
                Objects.equals(workAddress, employee.workAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, socialSecurity, salary, homePhone, homeAddress, workPhone, workAddress);
    }
}

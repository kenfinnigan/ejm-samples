package ejm.chapter2.employee.model;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Ken Finnigan
 */
@Entity
@Table(name = "EMPLOYEE")
@NamedQueries({
        @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e ORDER BY e.id")
})
@XmlRootElement
public class Employee {

    @Id
    @Column(name = "EMPLOYEE_ID", nullable = false, unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Size(min = 2, max = 200)
    @Column(name = "FIRST_NAME", nullable = false, length = 200)
    private String firstname;

    @NotNull
    @Size(min = 2, max = 150)
    @Column(name = "LAST_NAME", nullable = false, length = 150)
    private String lastname;

    @NotNull
    @Pattern(regexp = "^(\\d{3}-?\\d{2}-?\\d{4}|XXX-XX-XXXX)$")
    @Column(name = "SOCIAL_SECURITY_ID", nullable = false, length = 12)
    private String socialSecurity;

    @NotNull
    @Column(name = "SALARY", precision = 7, scale = 2)
    private BigDecimal salary;

    @Pattern(regexp = "^(\\d{3}-?\\d{3}-?\\d{4}|XXX-XXX-XXXX)$")
    @Column(name = "HOME_PHONE_NUMBER")
    private String homePhone;

    @Column(name = "HOME_ADDRESS_ID")
    private Integer homeAddressId;

    @Transient
    private Address homeAddress;

    @Pattern(regexp = "^(\\d{3}-?\\d{3}-?\\d{4}|XXX-XXX-XXXX)$")
    @Column(name = "WORK_PHONE_NUMBER")
    private String workPhone;

    @Column(name = "WORK_ADDRESS_ID")
    private Integer workAddressId;

    @Transient
    private Address workAddress;

    @PostLoad
    protected void init() {
        if (this.homeAddressId != null) {
            this.homeAddress = new Address(this.homeAddressId);
        }
        if (this.workAddressId != null) {
            this.workAddress = new Address(this.workAddressId);
        }
    }

    public Integer getId() {
        return id;
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

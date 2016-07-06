package ejm.chapter2.employee.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Ken Finnigan
 */
@Entity
@Table(name = "ADDRESS")
@NamedQueries({
        @NamedQuery(name = "Address.findAll", query = "SELECT a FROM Address a ORDER BY a.id")
})
@XmlRootElement
public class Address {

    @Id
    @Column(name = "ADDRESS_ID", nullable = false, unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Size(min = 2, max = 200)
    @Column(name = "FIRST_LINE", length = 200, nullable = false)
    private String firstLine;

    @Size(min = 2, max = 200)
    @Column(name = "SECOND_LINE", length = 200)
    private String secondLine;

    @Size(min = 3, max = 150)
    @Column(name = "CITY", length = 150, nullable = false)
    private String city;

    @Size(min = 2, max = 2)
    @Column(name = "STATE", length = 2, nullable = false)
    private String state;

    public Integer getId() {
        return id;
    }

    public String getFirstLine() {
        return firstLine;
    }

    public String getSecondLine() {
        return secondLine;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id) &&
                Objects.equals(firstLine, address.firstLine) &&
                Objects.equals(secondLine, address.secondLine) &&
                Objects.equals(city, address.city) &&
                Objects.equals(state, address.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstLine, secondLine, city, state);
    }
}

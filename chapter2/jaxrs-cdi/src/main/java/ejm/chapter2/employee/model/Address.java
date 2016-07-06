package ejm.chapter2.employee.model;

import java.util.Objects;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Ken Finnigan
 */
@XmlRootElement
public class Address {

    public Address(Integer id) {
        this.id = id;
    }

    public Address(Integer id, String firstLine, String secondLine, String city, String state) {
        this.id = id;
        this.firstLine = firstLine;
        this.secondLine = secondLine;
        this.city = city;
        this.state = state;
    }

    private Integer id;

    private String firstLine;

    private String secondLine;

    private String city;

    private String state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

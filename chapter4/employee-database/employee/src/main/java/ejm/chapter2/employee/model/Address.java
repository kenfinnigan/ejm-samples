package ejm.chapter2.employee.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Ken Finnigan
 */
@XmlRootElement
public class Address {

    public Address(Integer id) {
        this.id = id;
    }

    private Integer id;

    private String firstLine;

    private String secondLine;

    private String city;

    private String state;

    public Integer getId() {
        return this.id;
    }

    public String getFirstLine() {
        return firstLine;
    }

    public Address setFirstLine(String firstLine) {
        this.firstLine = firstLine;
        return this;
    }

    public String getSecondLine() {
        return secondLine;
    }

    public Address setSecondLine(String secondLine) {
        this.secondLine = secondLine;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Address setCity(String city) {
        this.city = city;
        return this;
    }

    public String getState() {
        return state;
    }

    public Address setState(String state) {
        this.state = state;
        return this;
    }
}

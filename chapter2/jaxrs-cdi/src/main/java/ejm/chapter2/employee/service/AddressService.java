package ejm.chapter2.employee.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import ejm.chapter2.employee.model.Address;

/**
 * @author Ken Finnigan
 */
@ApplicationScoped
public class AddressService {
    private int idCounter = 1;

    private Map<Integer, Address> addresses = new HashMap<>();

    @PostConstruct
    private void init() {
        Address address = new Address(idCounter++, "100 East Davie Street", null, "Raleigh", "NC");
        addresses.put(address.getId(), address);

        address = new Address(idCounter++, "314 Littleton Road", null, "Westford", "MA");
        addresses.put(address.getId(), address);

        address = new Address(idCounter++, "600 My Street", null, "Raleigh", "NC");
        addresses.put(address.getId(), address);

        address = new Address(idCounter++, "255 My Crescent", null, "Westford", "MA");
        addresses.put(address.getId(), address);
    }

    public List<Address> getAll() throws Exception {
        return addresses.values().stream().collect(Collectors.toList());
    }

    public Address create(Address address) throws Exception {
        address.setId(idCounter++);
        addresses.put(address.getId(), address);
        return address;
    }

    public Address retrieve(Integer addressId) {
        return addresses.get(addressId);
    }

    public void delete(Integer addressId) throws Exception {
        addresses.remove(addressId);
    }

    public Address save(Address address) throws Exception {
        addresses.put(address.getId(), address);
        return this.retrieve(address.getId());
    }

}

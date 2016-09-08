package ejm.chapter2.employee.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import ejm.chapter2.employee.model.Address;

/**
 * @author Ken Finnigan
 */
@ApplicationScoped
public class AddressService {

    @PersistenceContext
    EntityManager entityManager;

    public List<Address> getAll() throws Exception {
        return entityManager.createNamedQuery("Address.findAll", Address.class).getResultList();
    }

    @Transactional
    public Address create(Address address) throws Exception {
        entityManager.persist(address);
        return address;
    }

    public Address retrieve(Integer addressId) throws Exception {
        Address address = entityManager.find(Address.class, addressId);
        if (address == null) {
            throw new EntityNotFoundException("Address for id " + addressId + " was not found");
        }
        return address;
    }

    @Transactional
    public void delete(Integer addressId) throws Exception {
        Address address = entityManager.find(Address.class, addressId);

        if (address != null) {
            entityManager.remove(address);
        } else {
            throw new EntityNotFoundException("Address for id " + addressId + " was not found");
        }
    }

    @Transactional
    public Address save(Address address) throws Exception {
        entityManager.merge(address);
        return entityManager.find(Address.class, address.getId());
    }
}

package ejm.chapter2.employee.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
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
        return entityManager.find(Address.class, address.getId());
    }

    public Address retrieve(Integer addressId) throws Exception {
        return entityManager.find(Address.class, addressId);
    }

    @Transactional
    public boolean delete(Integer addressId) throws Exception {
        Address address = entityManager.find(Address.class, addressId);

        if (address != null) {
            entityManager.remove(address);
            return true;
        }

        return false;
    }

    @Transactional
    public Address save(Address address) throws Exception {
        entityManager.merge(address);
        return entityManager.find(Address.class, address.getId());
    }
}

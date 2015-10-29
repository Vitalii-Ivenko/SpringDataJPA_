package service;

import jpa.entity.Device;
import jpa.entity.Owner;
import jpa.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Vitalii Ivenko on 15.10.2015.
 */
@Transactional
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Transactional(readOnly = true)
    public List<Owner> getAllOwners() {
        List<Owner> owners = (List<Owner>) ownerRepository.findAll();
        return owners;
    }

    public void deleteOwnerByName(String name) {
        Owner owner = ownerRepository.findOwnerByName(name);
        ownerRepository.delete(owner);
    }


    public Owner deleteOneDeviceByName(String name) {
        Owner owner = ownerRepository.findOwnerByName(name);
        Device d = owner.getDevices().iterator().next();
        owner.getDevices().remove(d);
        return owner;
    }


    public Owner getOwnerByName(String name) {
        return ownerRepository.findOwnerByName(name);
    }

    public void save(Owner owner) {
        ownerRepository.save(owner);
    }

    public long count() {
        return ownerRepository.count();
    }

    public void deleteAll() {
        ownerRepository.deleteAll();
    }
}

package petclinic.springframwork.sbsapetclinic;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import petclinc.springframework.sbsapetclinic.SbsaPetClinicApplication;
import petclinc.springframework.sbsapetclinic.model.Owner;
import petclinc.springframework.sbsapetclinic.repositories.OwnerRepository;
import petclinc.springframework.sbsapetclinic.repositories.PetRepository;
import petclinc.springframework.sbsapetclinic.repositories.PetTypeRepository;
import petclinc.springframework.sbsapetclinic.services.springdatajpa.OwnerJpaService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SbsaPetClinicApplication.class)
public class OwnerJpaServiceIT {
    public static final String LAST_NAME = "daikhi";
    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private PetTypeRepository petTypeRepository;

    @Autowired
   private OwnerJpaService service;

    private Owner returnOwner;
    @Test
    public void contextLoads() {
        Assertions.assertThat(petRepository).isNotNull();
    }

    @Test
    void findByLastName() {

        Owner smith = ownerRepository.findByLastName(LAST_NAME);

        assertEquals(LAST_NAME, smith.getLastName());
    }

    @Test
    void findAll() {
        List<Owner> returnOwnersSet = ownerRepository.findAll();

        assertNotNull(returnOwnersSet);
        assertEquals(1, returnOwnersSet.size());
    }

    @Test
    void findById() {
        Owner savedOwner=ownerRepository.findAll().get(0);
        Owner owner = ownerRepository.findById(savedOwner.getId()).get();

        assertNotNull(owner);
    }

    @Test
    void findByIdNotFound() {


        Optional<Owner> owner = ownerRepository.findById(100L);

        assertThrows(NoSuchElementException.class,()->{
            owner.get();
        });
    }


    @Test
    void save() {

        Owner ownerToSave = Owner.builder().lastName(LAST_NAME).build();


        Owner savedOwner = ownerRepository.save(ownerToSave);
        assertEquals(savedOwner.getLastName(),LAST_NAME);
        assertNotNull(savedOwner);


    }

    @Test
    void delete() {
        Owner owner=ownerRepository.findAll().get(0);
        ownerRepository.delete(owner);


assertEquals(false,ownerRepository.existsById(owner.getId()));
    }

    @Test
    void deleteById() {
        Owner owner=ownerRepository.findAll().get(0);
        ownerRepository.deleteById(owner.getId());
        org.junit.jupiter.api.Assertions.assertNotEquals(true,ownerRepository.existsById(owner.getId()));
    }
}

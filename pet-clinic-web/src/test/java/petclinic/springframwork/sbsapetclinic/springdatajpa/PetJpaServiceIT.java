package petclinic.springframwork.sbsapetclinic.springdatajpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import petclinc.springframework.sbsapetclinic.SbsaPetClinicApplication;
import petclinc.springframework.sbsapetclinic.model.Pet;
import petclinc.springframework.sbsapetclinic.repositories.PetRepository;
import petclinc.springframework.sbsapetclinic.services.springdatajpa.PetJpaService;

import javax.transaction.Transactional;
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SbsaPetClinicApplication.class)
public class PetJpaServiceIT {
    String petName ="name";
    @Autowired
   private PetRepository petRepository;
    @Autowired
    private PetJpaService petJpaService;
    @Test
    void contextLoads() {
        Assertions.assertNotEquals(petRepository,null);
    }

    @Transactional
    @Test
    public void testSavePetName() throws Exception {
        //given
        Iterable<Pet> pets = petJpaService.findAll();
        Pet testPet = pets.iterator().next();


        //when
        testPet.setName(petName);
        Pet savedPet = petJpaService.save(testPet);

        //then
        Assertions.assertEquals(petName, savedPet.getName());
        Assertions.assertEquals(testPet.getId(), savedPet.getId());
        Assertions.assertEquals(testPet.getVisits().size(), savedPet.getVisits().size());
        //Assertions.assertEquals(testPet.getOwner().getFirstName(), savedPet.getOwner().getFirstName());
    }
    @Test
    public void deletePet(){
        //given
        Pet pet=petJpaService.findAll().stream().findAny().get();
        //when
        petJpaService.delete(pet);
        //then
        Assertions.assertEquals(false, petRepository.existsById(pet.getId()));
    }
    @Test
    public void findPet(){
        //given
        String new_pet = "new pet";
        Pet pet=Pet.builder().name(new_pet).build();
        //when
        Pet savedPet= petJpaService.save(pet);
        //then
        Assertions.assertEquals(true, petRepository.existsById(savedPet.getId()));
        Assertions.assertEquals(new_pet, petJpaService.findById(savedPet.getId()).getName());
    }
}

package petclinic.springframwork.sbsapetclinic.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import petclinc.springframework.sbsapetclinic.model.Pet;
import petclinc.springframework.sbsapetclinic.services.map.PetMapService;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class PetMapServiceTest {

    private PetMapService petMapService;

    private final Long petId1 = 1L;
    private final Long petId2 = 2L;

    @BeforeEach
    void setUp() {

        petMapService = new PetMapService();

        petMapService.save(Pet.builder().id(petId1).build());
        petMapService.save(Pet.builder().id(petId2).build());
    }

    @Test
    void findAll() {

        Set<Pet> petSet = petMapService.findAll();

        assertEquals(2, petSet.size());
    }

    @Test
    void findByIdExistingId() {

        Pet pet1 = petMapService.findById(petId1);
        Pet pet2 = petMapService.findById(petId2);

        assertEquals(petId1, pet1.getId());
        assertEquals(petId2, pet2.getId());
    }

    @Test
    void findByIdNotExistingId() {

        Pet pet = petMapService.findById(5L);

        assertNull(pet);
    }

    @Test
    void findByIdNullId() {

        Pet pet = petMapService.findById(null);

        assertNull(pet);
    }

    @Test
    void saveExistingId() {

        Long id = 2L;

        Pet pet2 = Pet.builder().id(id).build();

        Pet savedPet = petMapService.save(pet2);

        assertEquals(id, savedPet.getId());
    }

    @Test
    void saveDuplicateId() {

        Long id = 1L;

        Pet pet2 = Pet.builder().id(id).build();

        Pet savedPet = petMapService.save(pet2);

        assertEquals(id, savedPet.getId());
        assertEquals(2, petMapService.findAll().size());
    }

    @Test
    void saveNoId() {

        Pet savedPet = petMapService.save(Pet.builder().build());

        assertNotNull(savedPet);
        assertNotNull(savedPet.getId());
        assertEquals(3, petMapService.findAll().size());
    }

    @Test
    void deletePet() {

        petMapService.delete(petMapService.findById(petId1));

        assertEquals(1, petMapService.findAll().size());

    }

    @Test
    void deleteWithWrongId() {

        Pet pet = Pet.builder().id(11L).build();

        petMapService.delete(pet);

        assertEquals(2, petMapService.findAll().size());
    }

    @Test
    void deleteWithNullId() {

        Pet pet = Pet.builder().build();

        petMapService.delete(pet);

        assertEquals(2, petMapService.findAll().size());
    }

    @Test
    void deleteNull() {

        petMapService.delete(null);

        assertEquals(2, petMapService.findAll().size());

    }

    @Test
    void deleteByIdCorrectId() {

        petMapService.deleteById(petId1);

        assertEquals(1, petMapService.findAll().size());
    }

    @Test
    void deleteByIdWrongId() {

        petMapService.deleteById(5L);

        assertEquals(2, petMapService.findAll().size());
    }

    @Test
    void deleteByIdNullId() {

        petMapService.deleteById(null);

        assertEquals(2, petMapService.findAll().size());
    }
}
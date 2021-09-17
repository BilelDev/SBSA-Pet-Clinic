package petclinc.springframework.sbsapetclinic.bootstrap;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import petclinc.springframework.sbsapetclinic.model.*;
import petclinc.springframework.sbsapetclinic.repositories.*;

import java.time.LocalDate;

@Log4j2
@Component
public class LoadData implements CommandLineRunner {

    private final OwnerRepository ownerService;
    private final VetRepository vetService;
    private final PetTypeRepository petTypeService;
    private final SpecialtyRepository specialtyService;
    private final VisitRepository visitService;

    public LoadData(OwnerRepository ownerService, VetRepository vetService, PetTypeRepository petTypeService,
                      SpecialtyRepository specialtyService, VisitRepository visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if (count == 0 ){
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialtyService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialtyService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("dentistry");
        Speciality savedDentistry = specialtyService.save(dentistry);

        Owner bilel = new Owner();
        bilel.setFirstName("Bilel");
        bilel.setLastName("Daikhi");
        bilel.setAddress("123 Address");
        bilel.setCity("tunis");
        bilel.setTelephone("1231231234");

        Pet bilelPet = new Pet();
        bilelPet.setPetType(savedDogPetType);
        bilelPet.setOwner(bilel);
        bilelPet.setBirthDate(LocalDate.now());
        bilelPet.setName("bilel's pet ");
        bilel.getPets().add(bilelPet);

        ownerService.save(bilel);

        Owner ahmed = new Owner();
        ahmed.setFirstName("Ahmed");
        ahmed.setLastName("Daikhi");
        ahmed.setAddress("123 address");
        ahmed.setCity("tunis");
        ahmed.setTelephone("1231231234");

        Pet ahmedPet = new Pet();
        ahmedPet.setName("ahmed's pet");
        ahmedPet.setOwner(ahmed);
        ahmedPet.setBirthDate(LocalDate.now());
        ahmedPet.setPetType(savedCatPetType);
        ahmed.getPets().add(ahmedPet);

        ownerService.save(ahmed);

        Visit ahmedPetVisit = new Visit();
        ahmedPetVisit.setPet(ahmedPet);
        ahmedPetVisit.setDate(LocalDate.now());
        ahmedPetVisit.setDescription("sickness description");

        visitService.save(ahmedPetVisit);
        log.info("Loaded Owners....");


        Vet samiVet = new Vet();
        samiVet.setFirstName("Sami");
        samiVet.setLastName("Daikhi");
        samiVet.getSpecialities().add(savedRadiology);

        vetService.save(samiVet);

        Vet basmaVet = new Vet();
        basmaVet.setFirstName("Basma");
        basmaVet.setLastName("Daikhi");
        basmaVet.getSpecialities().add(savedSurgery);

        vetService.save(basmaVet);
        log.info("Loaded Vets....");

    }
}

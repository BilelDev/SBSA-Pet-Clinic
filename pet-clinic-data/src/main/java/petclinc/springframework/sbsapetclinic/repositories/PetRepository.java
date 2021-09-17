package petclinc.springframework.sbsapetclinic.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import petclinc.springframework.sbsapetclinic.model.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {
}

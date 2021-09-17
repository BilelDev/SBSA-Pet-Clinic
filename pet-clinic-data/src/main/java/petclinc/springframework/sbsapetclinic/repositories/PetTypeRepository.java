package petclinc.springframework.sbsapetclinic.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import petclinc.springframework.sbsapetclinic.model.PetType;

public interface PetTypeRepository extends JpaRepository<PetType, Long> {
}

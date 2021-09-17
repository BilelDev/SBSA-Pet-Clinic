package petclinc.springframework.sbsapetclinic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import petclinc.springframework.sbsapetclinic.model.Speciality;

public interface SpecialtyRepository extends JpaRepository<Speciality, Long> {
}

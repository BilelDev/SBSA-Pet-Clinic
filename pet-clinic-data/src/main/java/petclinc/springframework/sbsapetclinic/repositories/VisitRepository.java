package petclinc.springframework.sbsapetclinic.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import petclinc.springframework.sbsapetclinic.model.Visit;

public interface VisitRepository extends JpaRepository<Visit, Long> {
}

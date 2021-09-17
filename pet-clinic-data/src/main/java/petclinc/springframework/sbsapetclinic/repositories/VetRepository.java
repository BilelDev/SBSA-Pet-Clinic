package petclinc.springframework.sbsapetclinic.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import petclinc.springframework.sbsapetclinic.model.Vet;

public interface VetRepository extends JpaRepository<Vet, Long> {
}

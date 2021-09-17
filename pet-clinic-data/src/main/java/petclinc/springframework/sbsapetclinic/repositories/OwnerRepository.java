package petclinc.springframework.sbsapetclinic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import petclinc.springframework.sbsapetclinic.model.Owner;

import java.util.List;


public interface OwnerRepository extends JpaRepository<Owner, Long> {

    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
}

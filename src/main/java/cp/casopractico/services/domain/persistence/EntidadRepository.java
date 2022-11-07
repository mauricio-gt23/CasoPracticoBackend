package cp.casopractico.services.domain.persistence;

import cp.casopractico.services.domain.model.Entidad;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EntidadRepository extends JpaRepository<Entidad, Long> {
}

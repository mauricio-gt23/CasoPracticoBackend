package cp.casopractico.services.domain.persistence;

import cp.casopractico.services.domain.model.TipoContribuyente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoContribuyenteRepository extends JpaRepository<TipoContribuyente, Long> {
}

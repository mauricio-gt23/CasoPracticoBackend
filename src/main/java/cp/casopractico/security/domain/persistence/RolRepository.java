package cp.casopractico.security.domain.persistence;

import cp.casopractico.security.domain.model.entity.Rol;
import cp.casopractico.security.domain.model.enumeration.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    Rol getRolByNombre(Roles nombre);
    Optional<Rol> findByNombre(Roles name);
    boolean existsByNombre(Roles name);
}

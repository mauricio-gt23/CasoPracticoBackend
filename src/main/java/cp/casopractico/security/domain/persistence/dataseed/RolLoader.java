package cp.casopractico.security.domain.persistence.dataseed;

import cp.casopractico.security.domain.model.entity.Rol;
import cp.casopractico.security.domain.model.enumeration.Roles;
import cp.casopractico.security.domain.persistence.RolRepository;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class RolLoader {

    private final RolRepository rolRepository;

    private static String[] DEFAULT_ROLES = {"ROLE_ADMINISTRADOR" ,"ROLE_CLIENTE"};

    public RolLoader(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public void loadRoleData() {
        if (rolRepository.count() == 0) {
            Arrays.stream(DEFAULT_ROLES).forEach(name -> {
                Roles roleName = Roles.valueOf(name);
                if (!rolRepository.existsByNombre(roleName)) {
                    rolRepository.save(new Rol().withNombre(roleName));
                }
            });
        }
    }
}

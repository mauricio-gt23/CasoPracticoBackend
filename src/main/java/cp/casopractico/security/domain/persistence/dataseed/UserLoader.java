package cp.casopractico.security.domain.persistence.dataseed;

import cp.casopractico.security.domain.model.entity.Rol;
import cp.casopractico.security.domain.model.entity.Usuario;
import cp.casopractico.security.domain.model.enumeration.Roles;
import cp.casopractico.security.domain.persistence.RolRepository;
import cp.casopractico.security.domain.persistence.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserLoader {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    private final PasswordEncoder passwordEncoder;

    public UserLoader(UsuarioRepository usuarioRepository, RolRepository rolRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void loadUserData() {
        if (usuarioRepository.count() == 0) {

            Rol rol1 = rolRepository.getRolByNombre(Roles.valueOf("ROLE_ADMINISTRADOR"));
            Rol rol2 = rolRepository.getRolByNombre(Roles.valueOf("ROLE_CLIENTE"));

            Set<Rol> roles1 = new HashSet<>();
            roles1.add(rol1);

            Set<Rol> roles2 = new HashSet<>();
            roles2.add(rol2);

            Usuario usuario1 = new Usuario("mauricio123", "mauricio_carmen01@hotmail.com", passwordEncoder.encode("123") , "Mauricio", "Carmen", roles1);
            Usuario usuario2 = new Usuario("sebaz", "sebastian@hotmail.com", passwordEncoder.encode( "456"), "Sebastian", "Liñan", roles2);

            usuarioRepository.save(usuario1);
            usuarioRepository.save(usuario2);
        }
    }
}

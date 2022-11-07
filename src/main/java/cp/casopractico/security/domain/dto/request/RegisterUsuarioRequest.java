package cp.casopractico.security.domain.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class RegisterUsuarioRequest {
    private String username;
    private String email;
    private String password;
    private String name;
    private String lastName;
    private Set<String> roles;
}

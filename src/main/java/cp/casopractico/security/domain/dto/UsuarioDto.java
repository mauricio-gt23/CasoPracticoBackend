package cp.casopractico.security.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UsuarioDto {
    private String username;
    private String email;
    private String name;
    private String lastName;
    private List<String> roles;
}

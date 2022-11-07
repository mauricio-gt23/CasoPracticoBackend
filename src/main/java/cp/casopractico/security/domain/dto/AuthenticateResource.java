package cp.casopractico.security.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticateResource {
    private Long id;
    private String username;
    private String email;
    private String token;
    private List<String> roles;
}

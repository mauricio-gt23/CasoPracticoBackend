package cp.casopractico.security.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("securityMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public UsuarioMapper usuarioMapper() { return new UsuarioMapper(); }

    @Bean
    public RolMapper rolMapper() { return new RolMapper(); }

}

package cp.casopractico.services.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("serviceMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public TipoDocumentoMapper tipoDocumentoMapper() { return new TipoDocumentoMapper(); }

    @Bean
    public TipoContribuyenteMapper tipoContribuyenteMapper() { return new TipoContribuyenteMapper(); }

    @Bean
    public EntidadMapper entidadMapper() { return new EntidadMapper(); }
}

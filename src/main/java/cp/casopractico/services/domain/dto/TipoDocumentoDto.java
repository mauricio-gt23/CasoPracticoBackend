package cp.casopractico.services.domain.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class TipoDocumentoDto {
    private Long id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private Boolean estado;
}

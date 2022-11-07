package cp.casopractico.services.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TipoContribuyenteDto {
    private Long id;
    private String nombre;
    private Boolean estado;
}

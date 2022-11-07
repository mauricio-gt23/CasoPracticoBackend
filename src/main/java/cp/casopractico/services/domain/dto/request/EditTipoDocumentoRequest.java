package cp.casopractico.services.domain.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditTipoDocumentoRequest {
    private String codigo;
    private String nombre;
    private String descripcion;
    private Boolean estado;
}

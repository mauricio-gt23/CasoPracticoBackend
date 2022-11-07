package cp.casopractico.services.domain.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditEntidadRequest {
    private String nroDocumento;
    private String razonSocial;
    private String nombreComercial;
    private String direccion;
    private String telefono;
    private Boolean estado;
}

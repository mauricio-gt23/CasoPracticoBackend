package cp.casopractico.services.domain.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditTipoContribuyenteRequest {
    private String nombre;
    private Boolean estado;
}

package cp.casopractico.services.domain.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterTipoContribuyenteRequest {
    private String nombre;
    private Boolean estado;
}

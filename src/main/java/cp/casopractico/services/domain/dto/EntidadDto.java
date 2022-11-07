package cp.casopractico.services.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntidadDto {
    private Long id;
    private String nroDocumento;
    private String razonSocial;
    private String nombreComercial;
    private String direccion;
    private String telefono;
    private Boolean estado;
    private Long tipoDocumentoId;
    private Long tipoContribuyenteId;
}

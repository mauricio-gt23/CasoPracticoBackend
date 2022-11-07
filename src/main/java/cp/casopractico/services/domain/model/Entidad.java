package cp.casopractico.services.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "entidad")
public class Entidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 25, nullable = false, unique = true)
    private String nroDocumento;

    @Column(length = 100, nullable = false)
    private String razonSocial;

    @Column(length = 100)
    private String nombreComercial;

    @Column(length = 250)
    private String direccion;

    @Column(length = 50)
    private String telefono;

    @Column(nullable = false)
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "tipo_documento_id", nullable = false)
    private TipoDocumento tipoDocumento;

    @ManyToOne
    @JoinColumn(name = "tipo_contribuyente_id", nullable = false)
    private TipoContribuyente tipoContribuyente;

    public Entidad(Long id, String nroDocumento, String razonSocial, String nombreComercial, String direccion, String telefono, Boolean estado) {
        this.id = id;
        this.nroDocumento = nroDocumento;
        this.razonSocial = razonSocial;
        this.nombreComercial = nombreComercial;
        this.direccion = direccion;
        this.telefono = telefono;
        this.estado = estado;
    }

    public Entidad(String nroDocumento, String razonSocial, String nombreComercial, String direccion, String telefono, Boolean estado, TipoDocumento tipoDocumento, TipoContribuyente tipoContribuyente) {
        this.nroDocumento = nroDocumento;
        this.razonSocial = razonSocial;
        this.nombreComercial = nombreComercial;
        this.direccion = direccion;
        this.telefono = telefono;
        this.estado = estado;
        this.tipoDocumento = tipoDocumento;
        this.tipoContribuyente = tipoContribuyente;
    }
}

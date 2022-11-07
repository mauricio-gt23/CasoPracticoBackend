package cp.casopractico.services.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tipo_contribuyente")
public class TipoContribuyente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Boolean estado;

    @OneToMany(mappedBy = "tipoContribuyente")
    private Set<Entidad> entidades;

    public TipoContribuyente(Long id, String nombre, Boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
    }

    public TipoContribuyente(String nombre, Boolean estado) {
        this.nombre = nombre;
        this.estado = estado;
    }
}

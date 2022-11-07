package cp.casopractico.security.domain.model.entity;

import cp.casopractico.security.domain.model.enumeration.Roles;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@With
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rol")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Roles nombre;
}

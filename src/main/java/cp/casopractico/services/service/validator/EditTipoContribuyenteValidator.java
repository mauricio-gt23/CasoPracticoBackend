package cp.casopractico.services.service.validator;

import cp.casopractico.services.domain.dto.request.EditTipoContribuyenteRequest;
import cp.casopractico.services.domain.persistence.TipoContribuyenteRepository;
import cp.casopractico.shared.application.Notification;
import org.springframework.stereotype.Component;

@Component
public class EditTipoContribuyenteValidator {

    private final TipoContribuyenteRepository tipoContribuyenteRepository;

    public EditTipoContribuyenteValidator(TipoContribuyenteRepository tipoContribuyenteRepository) {
        this.tipoContribuyenteRepository = tipoContribuyenteRepository;
    }

    public Notification validate(Long tipoContribuyenteId, EditTipoContribuyenteRequest request) {
        Notification notification = new Notification();

        if (!tipoContribuyenteRepository.existsById(tipoContribuyenteId)) {
            notification.addError("Taxpayer Type Id doesn´t exist");
        }

        String nombre = request.getNombre() != null ? request.getNombre().trim() : "";
        if (nombre.isEmpty()) {
            notification.addError("Taxpayer Type nombre is required");
        }

        Boolean estado = request.getEstado();
        if (estado.toString().isEmpty()) {
            notification.addError("Taxpayer Type estado is required");
        }

        return notification;

    }

}

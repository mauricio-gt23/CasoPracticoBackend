package cp.casopractico.services.service.validator;

import cp.casopractico.services.domain.dto.request.EditEntidadRequest;
import cp.casopractico.services.domain.persistence.EntidadRepository;
import cp.casopractico.shared.application.Notification;
import org.springframework.stereotype.Component;

@Component
public class EditEntidadValidator {

    private EntidadRepository entidadRepository;

    public EditEntidadValidator(EntidadRepository entidadRepository) {
        this.entidadRepository = entidadRepository;
    }

    public Notification validate(Long entidadId, EditEntidadRequest request) {
        Notification notification = new Notification();

        if (!entidadRepository.existsById(entidadId)) {
            notification.addError("Entity Id doesn´t exist");
        }

        String nroDocumento = request.getNroDocumento() != null ? request.getNroDocumento().trim() : "";
        if (nroDocumento.isEmpty()) {
            notification.addError("Entity nroDocumento is required");
        }

        String razonSocial = request.getRazonSocial() != null ? request.getRazonSocial().trim() : "";
        if (razonSocial.isEmpty()) {
            notification.addError("Entity razonSocial is required");
        }

        return notification;
    }

}

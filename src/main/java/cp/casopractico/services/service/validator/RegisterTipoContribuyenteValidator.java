package cp.casopractico.services.service.validator;

import cp.casopractico.services.domain.dto.request.RegisterTipoContribuyenteRequest;
import cp.casopractico.shared.application.Notification;
import org.springframework.stereotype.Component;

@Component
public class RegisterTipoContribuyenteValidator {

    public RegisterTipoContribuyenteValidator() {}

    public Notification validate(RegisterTipoContribuyenteRequest request) {
        Notification notification = new Notification();

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

package cp.casopractico.services.service.validator;

import cp.casopractico.services.domain.dto.request.RegisterTipoDocumentoRequest;
import cp.casopractico.shared.application.Notification;
import org.springframework.stereotype.Component;

@Component
public class RegisterTipoDocumentoValidator {

    public RegisterTipoDocumentoValidator() { }

    public Notification validate(RegisterTipoDocumentoRequest request) {
        Notification notification = new Notification();

        String codigo = request.getCodigo() != null ? request.getCodigo().trim() : "";
        if (codigo.isEmpty()) {
            notification.addError("Document Type codigo is required");
        }

        String nombre = request.getNombre() != null ? request.getNombre().trim() : "";
        if (nombre.isEmpty()) {
            notification.addError("Document Type nombre is required");
        }

        Boolean estado = request.getEstado();
        if (estado.toString().isEmpty()) {
            notification.addError("Document Type estado is required");
        }

        return notification;
    }

}

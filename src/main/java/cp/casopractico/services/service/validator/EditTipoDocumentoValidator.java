package cp.casopractico.services.service.validator;

import cp.casopractico.services.domain.dto.request.EditTipoDocumentoRequest;
import cp.casopractico.services.domain.persistence.TipoDocumentoRepository;
import cp.casopractico.shared.application.Notification;
import org.springframework.stereotype.Component;

@Component
public class EditTipoDocumentoValidator {

    private final TipoDocumentoRepository tipoDocumentoRepository;

    public EditTipoDocumentoValidator(TipoDocumentoRepository tipoDocumentoRepository) {
        this.tipoDocumentoRepository = tipoDocumentoRepository;
    }

    public Notification validate(Long documentTypeId, EditTipoDocumentoRequest request) {
        Notification notification = new Notification();

        if (!tipoDocumentoRepository.existsById(documentTypeId)) {
            notification.addError("Document Type Id doesn´t exist");
        }

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

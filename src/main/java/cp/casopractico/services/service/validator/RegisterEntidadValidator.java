package cp.casopractico.services.service.validator;

import cp.casopractico.services.domain.dto.request.RegisterEntidadRequest;
import cp.casopractico.services.domain.persistence.TipoContribuyenteRepository;
import cp.casopractico.services.domain.persistence.TipoDocumentoRepository;
import cp.casopractico.shared.application.Notification;
import org.springframework.stereotype.Component;

@Component
public class RegisterEntidadValidator {

    private final TipoContribuyenteRepository tipoContribuyenteRepository;
    private final TipoDocumentoRepository tipoDocumentoRepository;

    public RegisterEntidadValidator(TipoContribuyenteRepository tipoContribuyenteRepository, TipoDocumentoRepository tipoDocumentoRepository) {
        this.tipoContribuyenteRepository = tipoContribuyenteRepository;
        this.tipoDocumentoRepository = tipoDocumentoRepository;
    }

    public Notification validate(RegisterEntidadRequest request) {
        Notification notification = new Notification();

        boolean tipoContribuyenteId = tipoContribuyenteRepository.existsById(request.getTipoContribuyenteId());
        if (!tipoContribuyenteId) {
            notification.addError("Taxpayer Type Id doesn't exist");
        }

        boolean tipoDocumentoId = tipoDocumentoRepository.existsById(request.getTipoDocumentoId());
        if (!tipoDocumentoId) {
            notification.addError("Document Type Id doesn't exist");
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

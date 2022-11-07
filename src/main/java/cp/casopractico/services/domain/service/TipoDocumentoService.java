package cp.casopractico.services.domain.service;

import cp.casopractico.services.domain.dto.request.EditTipoDocumentoRequest;
import cp.casopractico.services.domain.dto.request.RegisterTipoDocumentoRequest;
import cp.casopractico.services.domain.dto.TipoDocumentoDto;
import cp.casopractico.shared.application.Notification;
import cp.casopractico.shared.application.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TipoDocumentoService {
    Page<TipoDocumentoDto> getAll(Pageable pageable);
    TipoDocumentoDto getById(Long tipoDocumentoId);
    Result<TipoDocumentoDto, Notification> register(RegisterTipoDocumentoRequest request);
    Result<TipoDocumentoDto, Notification> edit(Long tipoDocumentoId, EditTipoDocumentoRequest request);
    ResponseEntity<Object> delete(Long tipoDocumentoId);
}

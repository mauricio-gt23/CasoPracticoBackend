package cp.casopractico.services.domain.service;

import cp.casopractico.services.domain.dto.EntidadDto;
import cp.casopractico.services.domain.dto.request.EditEntidadRequest;
import cp.casopractico.services.domain.dto.request.RegisterEntidadRequest;
import cp.casopractico.shared.application.Notification;
import cp.casopractico.shared.application.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EntidadService {
    Page<EntidadDto> getAll(Pageable pageable);
    EntidadDto getById(Long entidadId);
    Result<EntidadDto, Notification> register(RegisterEntidadRequest request);
    Result<EntidadDto, Notification> edit(Long entidadId, EditEntidadRequest request);
}

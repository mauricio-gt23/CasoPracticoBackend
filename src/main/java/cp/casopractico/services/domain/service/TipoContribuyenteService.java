package cp.casopractico.services.domain.service;

import cp.casopractico.services.domain.dto.TipoContribuyenteDto;
import cp.casopractico.services.domain.dto.request.EditTipoContribuyenteRequest;
import cp.casopractico.services.domain.dto.request.RegisterTipoContribuyenteRequest;
import cp.casopractico.shared.application.Notification;
import cp.casopractico.shared.application.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TipoContribuyenteService {
    Page<TipoContribuyenteDto> getAll(Pageable pageable);
    TipoContribuyenteDto getById(Long tipoContribuyenteId);
    Result<TipoContribuyenteDto, Notification> register(RegisterTipoContribuyenteRequest request);
    Result<TipoContribuyenteDto, Notification> edit(Long tipoContribuyenteId, EditTipoContribuyenteRequest request);
}

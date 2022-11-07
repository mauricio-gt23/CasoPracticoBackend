package cp.casopractico.security.domain.service;

import cp.casopractico.security.domain.dto.RolDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RolService {
    Page<RolDto> getAll(Pageable pageable);
}

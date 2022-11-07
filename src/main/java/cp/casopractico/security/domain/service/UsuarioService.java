package cp.casopractico.security.domain.service;

import cp.casopractico.security.domain.dto.UsuarioDto;
import cp.casopractico.security.domain.dto.request.AuthenticateRequest;
import cp.casopractico.security.domain.dto.request.RegisterUsuarioRequest;
import cp.casopractico.shared.application.Notification;
import cp.casopractico.shared.application.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsuarioService extends UserDetailsService {
    Page<UsuarioDto> getAll(Pageable pageable);
    UsuarioDto getById(Long userId);
    ResponseEntity<?> authenticate(AuthenticateRequest request);
    Result<UsuarioDto, Notification> register(RegisterUsuarioRequest request);
}

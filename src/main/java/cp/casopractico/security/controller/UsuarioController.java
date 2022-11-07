package cp.casopractico.security.controller;

import cp.casopractico.security.domain.dto.UsuarioDto;
import cp.casopractico.security.domain.dto.request.AuthenticateRequest;
import cp.casopractico.security.domain.dto.request.RegisterUsuarioRequest;
import cp.casopractico.security.domain.service.UsuarioService;
import cp.casopractico.services.domain.dto.EntidadDto;
import cp.casopractico.shared.api.ApiController;
import cp.casopractico.shared.application.Notification;
import cp.casopractico.shared.application.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/users")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/auth/sign-in")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthenticateRequest request) {
        return usuarioService.authenticate(request);
    }

    @PostMapping("/auth/sign-up")
    public ResponseEntity<Object> registerUser(@RequestBody RegisterUsuarioRequest request) {
        try {
            Result<UsuarioDto, Notification> result = usuarioService.register(request);
            if (result.isSuccess()) {
                return ApiController.created(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch (Exception e) {
            return ApiController.serverError();
        }
    }

    @GetMapping()
    @PreAuthorize("hasRole('ADMINISTRADOR') or hasRole('CLIENTE')")
    public ResponseEntity<Object> getAllUsers(Pageable pageable) {
        Page<UsuarioDto> resources = usuarioService.getAll(pageable);
        return ApiController.ok(resources);
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasRole('ADMINISTRADOR') or hasRole('CLIENTE')")
    public ResponseEntity<Object> getByIdUser(@PathVariable("userId") Long userTypeId) {
        try {
            UsuarioDto usuarioDto = usuarioService.getById(userTypeId);
            return ApiController.ok(usuarioDto);
        } catch (Exception e) {
            return ApiController.notFound();
        }
    }
}

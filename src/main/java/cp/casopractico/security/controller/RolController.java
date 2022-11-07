package cp.casopractico.security.controller;

import cp.casopractico.security.domain.dto.RolDto;
import cp.casopractico.security.domain.service.RolService;
import cp.casopractico.shared.api.ApiController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/roles")
public class RolController {

    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @GetMapping()
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Object> getAllRoles(Pageable pageable) {
        Page<RolDto> resources = rolService.getAll(pageable);
        return ApiController.ok(resources);
    }

}

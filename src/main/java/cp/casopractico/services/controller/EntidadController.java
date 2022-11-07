package cp.casopractico.services.controller;

import cp.casopractico.services.domain.dto.EntidadDto;
import cp.casopractico.services.domain.dto.request.EditEntidadRequest;
import cp.casopractico.services.domain.dto.request.RegisterEntidadRequest;
import cp.casopractico.services.domain.service.EntidadService;
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
@RequestMapping(path="/api/v1/entities")
public class EntidadController {

    private final EntidadService entidadService;

    public EntidadController(EntidadService entidadService) {
        this.entidadService = entidadService;
    }


    @Operation(summary = "Get Entites", description = "Get All Entites")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Entities found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = EntidadDto.class
                                    )
                            )
                    }
            )
    })
    @GetMapping()
    @PreAuthorize("hasRole('ADMINISTRADOR') or hasRole('CLIENTE')")
    public ResponseEntity<Object> getAllEntities(Pageable pageable) {
        Page<EntidadDto> resources = entidadService.getAll(pageable);
        return ApiController.ok(resources);
    }

    @Operation(summary = "Get Entity By Id", description = "Get Entity already stored by Id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Entity found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = EntidadDto.class
                                    )
                            )
                    }
            )
    })
    @GetMapping("/{entityId}")
    @PreAuthorize("hasRole('ADMINISTRADOR') or hasRole('CLIENTE')")
    public ResponseEntity<Object> getByIdEntity(@PathVariable("entityId") Long entityTypeId) {
        try {
            EntidadDto entidadDto = entidadService.getById(entityTypeId);
            return ApiController.ok(entidadDto);
        } catch (Exception e) {
            return ApiController.notFound();
        }
    }

    @Operation(summary = "Create Entity", description = "Create a new Entity")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Entities created",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = EntidadDto.class
                                    )
                            )
                    }
            )
    })
    @PostMapping()
    @PreAuthorize("hasRole('ADMINISTRADOR') or hasRole('CLIENTE')")
    public ResponseEntity<Object> registerEntity(@RequestBody RegisterEntidadRequest request) {
        try {
            Result<EntidadDto, Notification> result = entidadService.register(request);
            if (result.isSuccess()) {
                return ApiController.created(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch (Exception e) {
            return ApiController.serverError();
        }
    }

    @Operation(summary = "Update Entity", description = "Update Entity already stored by Id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Entity updated",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = EntidadDto.class
                                    )
                            )
                    }
            )
    })
    @PutMapping("/{entityId}")
    @PreAuthorize("hasRole('ADMINISTRADOR') or hasRole('CLIENTE')")
    public ResponseEntity<Object> editEntity(@PathVariable("entityId") Long entityId, @RequestBody EditEntidadRequest request) {
        try {
            Result<EntidadDto, Notification> result = entidadService.edit(entityId, request);
            if (result.isSuccess()) {
                return ApiController.ok(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch (Exception e) {
            return ApiController.serverError();
        }
    }

}

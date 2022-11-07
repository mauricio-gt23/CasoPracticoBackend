package cp.casopractico.services.controller;

import cp.casopractico.services.domain.dto.TipoDocumentoDto;
import cp.casopractico.services.domain.dto.request.EditTipoDocumentoRequest;
import cp.casopractico.services.domain.dto.request.RegisterTipoDocumentoRequest;
import cp.casopractico.services.domain.service.TipoDocumentoService;
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
@RequestMapping(path="/api/v1/documentstype")
public class TipoDocumentoController {

    private final TipoDocumentoService tipoDocumentoService;

    public TipoDocumentoController(TipoDocumentoService tipoDocumentoService) {
        this.tipoDocumentoService = tipoDocumentoService;
    }

    @Operation(summary = "Get Documents Type", description = "Get All Documents Type")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Documents Type found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = TipoDocumentoDto.class
                                    )
                            )
                    }
            )
    })
    @GetMapping()
    @PreAuthorize("hasRole('ADMINISTRADOR') or hasRole('CLIENTE')")
    public ResponseEntity<Object> getAllDocumentType(Pageable pageable) {
        Page<TipoDocumentoDto> resources = tipoDocumentoService.getAll(pageable);
        return ApiController.ok(resources);
    }

    @Operation(summary = "Get Document Type By Id", description = "Get Document Type already stored by Id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Document Type found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = TipoDocumentoDto.class
                                    )
                            )
                    }
            )
    })
    @GetMapping("/{documenttypeId}")
    @PreAuthorize("hasRole('ADMINISTRADOR') or hasRole('CLIENTE')")
    public ResponseEntity<Object> getByIdDocumentType(@PathVariable("documenttypeId") Long documentTypeId) {
        try {
            TipoDocumentoDto tipoDocumentoDto = tipoDocumentoService.getById(documentTypeId);
            return ApiController.ok(tipoDocumentoDto);
        } catch (Exception e) {
            return ApiController.notFound();
        }
    }

    @Operation(summary = "Create Document Type", description = "Create a Document Type")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Document Type created",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = TipoDocumentoDto.class
                                    )
                            )
                    }
            )
    })
    @PostMapping()
    @PreAuthorize("hasRole('ADMINISTRADOR') or hasRole('CLIENTE')")
    public ResponseEntity<Object> registerDocumentType(@RequestBody RegisterTipoDocumentoRequest request) {
        try {
            Result<TipoDocumentoDto, Notification> result = tipoDocumentoService.register(request);
            if (result.isSuccess()) {
                return ApiController.created(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch (Exception e) {
            return ApiController.serverError();
        }
    }

    @Operation(summary = "Update Document Type", description = "Update Document Type already stored by Id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Document Type updated",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = TipoDocumentoDto.class
                                    )
                            )
                    }
            )
    })
    @PutMapping("/{documenttypeId}")
    @PreAuthorize("hasRole('ADMINISTRADOR') or hasRole('CLIENTE')")
    public ResponseEntity<Object> editDocumentType(@PathVariable("documenttypeId") Long documentTypeId, @RequestBody EditTipoDocumentoRequest request) {
        try {
            Result<TipoDocumentoDto, Notification> result = tipoDocumentoService.edit(documentTypeId, request);
            if (result.isSuccess()) {
                return ApiController.ok(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch (Exception e) {
            return ApiController.serverError();
        }
    }

    @Operation(summary = "Delete Document Type", description = "Delete Document Type already stored")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Document Type deleted",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }
            )
    })
    @DeleteMapping("/{documenttypeId}")
    @PreAuthorize("hasRole('ADMINISTRADOR') or hasRole('CLIENTE')")
    public ResponseEntity<Object> deleteDocumentType(@PathVariable("documenttypeId") Long documentTypeId) {
        try {
            return tipoDocumentoService.delete(documentTypeId);
        } catch (Exception e) {
            return ApiController.serverError();
        }
    }
}

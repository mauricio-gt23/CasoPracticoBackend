package cp.casopractico.services.controller;

import cp.casopractico.services.domain.dto.TipoContribuyenteDto;
import cp.casopractico.services.domain.dto.request.EditTipoContribuyenteRequest;
import cp.casopractico.services.domain.dto.request.RegisterTipoContribuyenteRequest;
import cp.casopractico.services.domain.service.TipoContribuyenteService;
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
@RequestMapping(path="/api/v1/taxpayerstype")
public class TipoContribuyenteController {

    private final TipoContribuyenteService tipoContribuyenteService;

    public TipoContribuyenteController(TipoContribuyenteService tipoContribuyenteService) {
        this.tipoContribuyenteService = tipoContribuyenteService;
    }

    @Operation(summary = "Get Taxpayers Type", description = "Get All Taxpayers Type")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Taxpayers Type found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = TipoContribuyenteDto.class
                                    )
                            )
                    }
            )
    })
    @GetMapping()
    @PreAuthorize("hasRole('ADMINISTRADOR') or hasRole('CLIENTE')")
    public ResponseEntity<Object> getAllTaxpayerType(Pageable pageable) {
        Page<TipoContribuyenteDto> resources = tipoContribuyenteService.getAll(pageable);
        return ApiController.ok(resources);
    }

    @Operation(summary = "Get Taxpayer Type By Id", description = "Get Taxpayer Type already stored by Id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Taxpayer Type found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = TipoContribuyenteDto.class
                                    )
                            )
                    }
            )
    })
    @GetMapping("/{taxpayertypeId}")
    @PreAuthorize("hasRole('ADMINISTRADOR') or hasRole('CLIENTE')")
    public ResponseEntity<Object> getByIdTaxpayerType(@PathVariable("taxpayertypeId") Long taxpayersTypeId) {
        try {
            TipoContribuyenteDto tipoContribuyenteDto = tipoContribuyenteService.getById(taxpayersTypeId);
            return ApiController.ok(tipoContribuyenteDto);
        } catch (Exception e) {
            return ApiController.notFound();
        }
    }

    @Operation(summary = "Create Taxpayer Type", description = "Create a Taxpayer Type")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Taxpayer Type created",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = TipoContribuyenteDto.class
                                    )
                            )
                    }
            )
    })
    @PostMapping()
    @PreAuthorize("hasRole('ADMINISTRADOR') or hasRole('CLIENTE')")
    public ResponseEntity<Object> registerTaxpayerType(@RequestBody RegisterTipoContribuyenteRequest request) {
        try {
            Result<TipoContribuyenteDto, Notification> result = tipoContribuyenteService.register(request);
            if (result.isSuccess()) {
                return ApiController.created(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch (Exception e) {
            return ApiController.serverError();
        }
    }

    @Operation(summary = "Update Taxpayer Type", description = "Update Taxpayer Type already stored by Id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Taxpayer Type updated",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = TipoContribuyenteDto.class
                                    )
                            )
                    }
            )
    })
    @PutMapping("/{taxpayertypeId}")
    @PreAuthorize("hasRole('ADMINISTRADOR') or hasRole('CLIENTE')")
    public ResponseEntity<Object> editTaxpayerType(@PathVariable("taxpayertypeId") Long taxpayerstypeId, @RequestBody EditTipoContribuyenteRequest request) {
        try {
            Result<TipoContribuyenteDto, Notification> result = tipoContribuyenteService.edit(taxpayerstypeId, request);
            if (result.isSuccess()) {
                return ApiController.ok(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch (Exception e) {
            return ApiController.serverError();
        }
    }

}

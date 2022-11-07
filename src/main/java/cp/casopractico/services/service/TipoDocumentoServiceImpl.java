package cp.casopractico.services.service;

import cp.casopractico.services.domain.dto.TipoDocumentoDto;
import cp.casopractico.services.domain.dto.request.EditTipoDocumentoRequest;
import cp.casopractico.services.domain.dto.request.RegisterTipoDocumentoRequest;
import cp.casopractico.services.domain.model.TipoDocumento;
import cp.casopractico.services.domain.persistence.TipoDocumentoRepository;
import cp.casopractico.services.domain.service.TipoDocumentoService;
import cp.casopractico.services.mapping.TipoDocumentoMapper;
import cp.casopractico.services.service.validator.EditTipoDocumentoValidator;
import cp.casopractico.services.service.validator.RegisterTipoDocumentoValidator;
import cp.casopractico.shared.api.ApiController;
import cp.casopractico.shared.application.Notification;
import cp.casopractico.shared.application.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TipoDocumentoServiceImpl implements TipoDocumentoService {

    private final TipoDocumentoRepository tipoDocumentoRepository;
    private final RegisterTipoDocumentoValidator registerTipoDocumentoValidator;
    private final EditTipoDocumentoValidator editTipoDocumentoValidator;
    private final TipoDocumentoMapper mapper;

    public TipoDocumentoServiceImpl(TipoDocumentoRepository tipoDocumentoRepository, RegisterTipoDocumentoValidator registerTipoDocumentoValidator, EditTipoDocumentoValidator editTipoDocumentoValidator, TipoDocumentoMapper mapper) {
        this.tipoDocumentoRepository = tipoDocumentoRepository;
        this.registerTipoDocumentoValidator = registerTipoDocumentoValidator;
        this.editTipoDocumentoValidator = editTipoDocumentoValidator;
        this.mapper = mapper;
    }

    @Override
    public Page<TipoDocumentoDto> getAll(Pageable pageable) {
        return mapper.modelListToPage(tipoDocumentoRepository.findAll(), pageable);
    }

    @Override
    public TipoDocumentoDto getById(Long tipoDocumentoId) {
        return mapper.toResource(tipoDocumentoRepository.getReferenceById(tipoDocumentoId));
    }

    @Override
    public Result<TipoDocumentoDto, Notification> register(RegisterTipoDocumentoRequest request) {
        Notification notification = this.registerTipoDocumentoValidator.validate(request);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }

        TipoDocumento tipoDocumento = new TipoDocumento(
                request.getCodigo(),
                request.getNombre(),
                request.getDescripcion(),
                request.getEstado()
        );

        tipoDocumentoRepository.save(tipoDocumento);
        TipoDocumentoDto resource = mapper.toResource(tipoDocumento);
        return Result.success(resource);
    }

    @Override
    public Result<TipoDocumentoDto, Notification> edit(Long tipoDocumentoId, EditTipoDocumentoRequest request) {
        Notification notification = this.editTipoDocumentoValidator.validate(tipoDocumentoId, request);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }

        this.tipoDocumentoRepository.findById(tipoDocumentoId).map(tipoDocumento -> {
            tipoDocumento.setCodigo(request.getCodigo());
            tipoDocumento.setNombre(request.getNombre());
            tipoDocumento.setDescripcion(request.getDescripcion());
            tipoDocumento.setEstado(request.getEstado());
            tipoDocumentoRepository.save(tipoDocumento);
            return null;
        });

        TipoDocumento tipoDocumento = new TipoDocumento(
                tipoDocumentoId,
                request.getCodigo(),
                request.getNombre(),
                request.getDescripcion(),
                request.getEstado()
        );

        TipoDocumentoDto resource = mapper.toResource(tipoDocumento);
        return Result.success(resource);
    }

    @Override
    public ResponseEntity<Object> delete(Long tipoDocumentoId) {
        return tipoDocumentoRepository.findById(tipoDocumentoId).map(tipoDocumento -> {
            tipoDocumentoRepository.delete(tipoDocumento);
            return ResponseEntity.ok().build();
        }).orElse(ApiController.notFound());
    }
}

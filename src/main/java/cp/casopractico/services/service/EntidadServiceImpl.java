package cp.casopractico.services.service;

import cp.casopractico.services.domain.dto.EntidadDto;
import cp.casopractico.services.domain.dto.request.EditEntidadRequest;
import cp.casopractico.services.domain.dto.request.RegisterEntidadRequest;
import cp.casopractico.services.domain.model.Entidad;
import cp.casopractico.services.domain.model.TipoContribuyente;
import cp.casopractico.services.domain.model.TipoDocumento;
import cp.casopractico.services.domain.persistence.EntidadRepository;
import cp.casopractico.services.domain.persistence.TipoContribuyenteRepository;
import cp.casopractico.services.domain.persistence.TipoDocumentoRepository;
import cp.casopractico.services.domain.service.EntidadService;
import cp.casopractico.services.mapping.EntidadMapper;
import cp.casopractico.services.service.validator.EditEntidadValidator;
import cp.casopractico.services.service.validator.RegisterEntidadValidator;
import cp.casopractico.shared.application.Notification;
import cp.casopractico.shared.application.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EntidadServiceImpl implements EntidadService {

    private final EntidadRepository entidadRepository;
    private final TipoDocumentoRepository tipoDocumentoRepository;
    private final TipoContribuyenteRepository tipoContribuyenteRepository;
    private final RegisterEntidadValidator registerEntidadValidator;
    private final EditEntidadValidator editEntidadValidator;
    private final EntidadMapper mapper;

    public EntidadServiceImpl(EntidadRepository entidadRepository, TipoDocumentoRepository tipoDocumentoRepository, TipoContribuyenteRepository tipoContribuyenteRepository, RegisterEntidadValidator registerEntidadValidator, EditEntidadValidator editEntidadValidator, EntidadMapper mapper) {
        this.entidadRepository = entidadRepository;
        this.tipoDocumentoRepository = tipoDocumentoRepository;
        this.tipoContribuyenteRepository = tipoContribuyenteRepository;
        this.registerEntidadValidator = registerEntidadValidator;
        this.editEntidadValidator = editEntidadValidator;
        this.mapper = mapper;
    }

    @Override
    public Page<EntidadDto> getAll(Pageable pageable) {
        return mapper.modelListToPage(entidadRepository.findAll(), pageable);
    }

    @Override
    public EntidadDto getById(Long entidadId) {
        return mapper.toResource(entidadRepository.getReferenceById(entidadId));
    }

    @Override
    public Result<EntidadDto, Notification> register(RegisterEntidadRequest request) {
        Notification notification = this.registerEntidadValidator.validate(request);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }

        TipoDocumento tipoDocumento = tipoDocumentoRepository.getReferenceById(request.getTipoDocumentoId());
        TipoContribuyente tipoContribuyente = tipoContribuyenteRepository.getReferenceById(request.getTipoContribuyenteId());

        Entidad entidad = new Entidad(
                request.getNroDocumento(),
                request.getRazonSocial(),
                request.getNombreComercial(),
                request.getDireccion(),
                request.getTelefono(),
                request.getEstado(),
                tipoDocumento,
                tipoContribuyente
        );

        entidadRepository.save(entidad);
        EntidadDto resource = mapper.toResource(entidad);
        return Result.success(resource);
    }

    @Override
    public Result<EntidadDto, Notification> edit(Long entidadId, EditEntidadRequest request) {
        Notification notification = this.editEntidadValidator.validate(entidadId, request);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }


        this.entidadRepository.findById(entidadId).map(entidad -> {
            entidad.setNroDocumento(request.getNroDocumento());
            entidad.setRazonSocial(request.getRazonSocial());
            entidad.setNombreComercial(request.getNombreComercial());
            entidad.setDireccion(request.getDireccion());
            entidad.setTelefono(request.getTelefono());
            entidad.setEstado(request.getEstado());
            this.entidadRepository.save(entidad);
            return null;
        });

        Entidad entidad = entidadRepository.getReferenceById(entidadId);
        EntidadDto resource = mapper.toResource(entidad);
        return Result.success(resource);
    }
}

package cp.casopractico.services.service;

import cp.casopractico.services.domain.dto.TipoContribuyenteDto;
import cp.casopractico.services.domain.dto.request.EditTipoContribuyenteRequest;
import cp.casopractico.services.domain.dto.request.RegisterTipoContribuyenteRequest;
import cp.casopractico.services.domain.model.TipoContribuyente;
import cp.casopractico.services.domain.persistence.TipoContribuyenteRepository;
import cp.casopractico.services.domain.service.TipoContribuyenteService;
import cp.casopractico.services.mapping.TipoContribuyenteMapper;
import cp.casopractico.services.service.validator.EditTipoContribuyenteValidator;
import cp.casopractico.services.service.validator.RegisterTipoContribuyenteValidator;
import cp.casopractico.shared.application.Notification;
import cp.casopractico.shared.application.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TipoContribuyenteServiceImpl implements TipoContribuyenteService {

    private final TipoContribuyenteRepository tipoContribuyenteRepository;
    private final RegisterTipoContribuyenteValidator registerTipoContribuyenteValidator;
    private final EditTipoContribuyenteValidator editTipoContribuyenteValidator;
    private final TipoContribuyenteMapper mapper;

    public TipoContribuyenteServiceImpl(TipoContribuyenteRepository tipoContribuyenteRepository, RegisterTipoContribuyenteValidator registerTipoContribuyenteValidator, EditTipoContribuyenteValidator editTipoContribuyenteValidator, TipoContribuyenteMapper mapper) {
        this.tipoContribuyenteRepository = tipoContribuyenteRepository;
        this.registerTipoContribuyenteValidator = registerTipoContribuyenteValidator;
        this.editTipoContribuyenteValidator = editTipoContribuyenteValidator;
        this.mapper = mapper;
    }

    @Override
    public Page<TipoContribuyenteDto> getAll(Pageable pageable) {
        return mapper.modelListToPage(tipoContribuyenteRepository.findAll(), pageable);
    }

    @Override
    public TipoContribuyenteDto getById(Long tipoContribuyenteId) {
        return mapper.toResource(tipoContribuyenteRepository.getReferenceById(tipoContribuyenteId));
    }

    @Override
    public Result<TipoContribuyenteDto, Notification> register(RegisterTipoContribuyenteRequest request) {
        Notification notification = this.registerTipoContribuyenteValidator.validate(request);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }

        TipoContribuyente tipoContribuyente = new TipoContribuyente(
                request.getNombre(),
                request.getEstado()
        );

        tipoContribuyenteRepository.save(tipoContribuyente);
        TipoContribuyenteDto resource = mapper.toResource(tipoContribuyente);
        return Result.success(resource);
    }

    @Override
    public Result<TipoContribuyenteDto, Notification> edit(Long tipoContribuyenteId, EditTipoContribuyenteRequest request) {
        Notification notification = this.editTipoContribuyenteValidator.validate(tipoContribuyenteId, request);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }

        this.tipoContribuyenteRepository.findById(tipoContribuyenteId).map(tipoContribuyente -> {
            tipoContribuyente.setNombre(request.getNombre());
            tipoContribuyente.setEstado(request.getEstado());
            this.tipoContribuyenteRepository.save(tipoContribuyente);
            return null;
        });

        TipoContribuyente tipoContribuyente = new TipoContribuyente(
                tipoContribuyenteId,
                request.getNombre(),
                request.getEstado()
        );

        TipoContribuyenteDto resource = mapper.toResource(tipoContribuyente);
        return Result.success(resource);
    }
}

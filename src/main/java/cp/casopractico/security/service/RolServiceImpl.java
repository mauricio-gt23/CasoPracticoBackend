package cp.casopractico.security.service;

import cp.casopractico.security.domain.dto.RolDto;
import cp.casopractico.security.domain.persistence.RolRepository;
import cp.casopractico.security.domain.service.RolService;
import cp.casopractico.security.mapping.RolMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;
    private final RolMapper mapper;

    public RolServiceImpl(RolRepository rolRepository, RolMapper mapper) {
        this.rolRepository = rolRepository;
        this.mapper = mapper;
    }

    @Override
    public Page<RolDto> getAll(Pageable pageable) {
        return mapper.modelListToPage(rolRepository.findAll(), pageable);
    }
}

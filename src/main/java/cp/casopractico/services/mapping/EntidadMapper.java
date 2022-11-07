package cp.casopractico.services.mapping;

import cp.casopractico.services.domain.dto.EntidadDto;
import cp.casopractico.services.domain.dto.TipoContribuyenteDto;
import cp.casopractico.services.domain.model.Entidad;
import cp.casopractico.services.domain.model.TipoContribuyente;
import cp.casopractico.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class EntidadMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public Page<EntidadDto> modelListToPage(List<Entidad> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, EntidadDto.class), pageable, modelList.size());
    }

    public EntidadDto toResource(Entidad model) {
        return mapper.map(model, EntidadDto.class);
    }

}

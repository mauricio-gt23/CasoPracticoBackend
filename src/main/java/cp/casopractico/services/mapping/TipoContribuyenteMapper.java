package cp.casopractico.services.mapping;

import cp.casopractico.services.domain.dto.TipoContribuyenteDto;
import cp.casopractico.services.domain.dto.TipoDocumentoDto;
import cp.casopractico.services.domain.model.TipoContribuyente;
import cp.casopractico.services.domain.model.TipoDocumento;
import cp.casopractico.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class TipoContribuyenteMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public Page<TipoContribuyenteDto> modelListToPage(List<TipoContribuyente> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, TipoContribuyenteDto.class), pageable, modelList.size());
    }

    public TipoContribuyenteDto toResource(TipoContribuyente model) {
        return mapper.map(model, TipoContribuyenteDto.class);
    }
}

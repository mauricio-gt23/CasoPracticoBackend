package cp.casopractico.services.mapping;

import cp.casopractico.services.domain.dto.TipoDocumentoDto;
import cp.casopractico.services.domain.model.TipoDocumento;
import cp.casopractico.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class TipoDocumentoMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public Page<TipoDocumentoDto> modelListToPage(List<TipoDocumento> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, TipoDocumentoDto.class), pageable, modelList.size());
    }

    public TipoDocumentoDto toResource(TipoDocumento model) {
        return mapper.map(model, TipoDocumentoDto.class);
    }

}

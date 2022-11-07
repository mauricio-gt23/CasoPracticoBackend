package cp.casopractico.security.mapping;

import cp.casopractico.security.domain.dto.UsuarioDto;
import cp.casopractico.security.domain.model.entity.Rol;
import cp.casopractico.security.domain.model.entity.Usuario;
import cp.casopractico.shared.mapping.EnhancedModelMapper;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class UsuarioMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    Converter<Rol, String> roleToString = new AbstractConverter<Rol, String>() {
        @Override
        protected String convert(Rol rol) {
            return rol == null ? null : rol.getNombre().name();
        }
    };

    public UsuarioDto toResource(Usuario model) {
        mapper.addConverter(roleToString);
        return mapper.map(model, UsuarioDto.class);
    }

    public Page<UsuarioDto> modelListToPage(List<Usuario> modelList, Pageable pageable) {
        mapper.addConverter(roleToString);
        return new PageImpl<>(mapper.mapList(modelList, UsuarioDto.class), pageable, modelList.size());
    }
}

package cp.casopractico.security.mapping;

import cp.casopractico.security.domain.dto.RolDto;
import cp.casopractico.security.domain.model.entity.Rol;
import cp.casopractico.security.domain.model.enumeration.Roles;
import cp.casopractico.shared.mapping.EnhancedModelMapper;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class RolMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    Converter<Roles, String> rolesToString = new AbstractConverter<Roles, String>() {
        @Override
        protected String convert(Roles role) {
            return role == null ? null : role.name();
        }
    };

    // Object Mapping
    public RolDto toResource(Rol model) {
        mapper.addConverter(rolesToString);
        return mapper.map(model, RolDto.class);
    }

    public Page<RolDto> modelListToPage(List<Rol> modelList, Pageable pageable) {
        mapper.addConverter(rolesToString);
        return new PageImpl<>(mapper.mapList(modelList, RolDto.class), pageable, modelList.size());
    }

}

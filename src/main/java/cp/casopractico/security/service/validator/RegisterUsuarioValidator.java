package cp.casopractico.security.service.validator;

import cp.casopractico.security.domain.dto.request.RegisterUsuarioRequest;
import cp.casopractico.security.domain.persistence.RolRepository;
import cp.casopractico.shared.application.Notification;
import org.springframework.stereotype.Component;

@Component
public class RegisterUsuarioValidator {

    public RegisterUsuarioValidator() {}

    public Notification validate(RegisterUsuarioRequest request) {
        Notification notification = new Notification();


        return notification;
    }
}

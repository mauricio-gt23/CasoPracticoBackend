package cp.casopractico.security.service;

import cp.casopractico.security.domain.dto.AuthenticateResource;
import cp.casopractico.security.domain.dto.UsuarioDto;
import cp.casopractico.security.domain.dto.request.AuthenticateRequest;
import cp.casopractico.security.domain.dto.request.RegisterUsuarioRequest;
import cp.casopractico.security.domain.model.entity.Rol;
import cp.casopractico.security.domain.model.entity.Usuario;
import cp.casopractico.security.domain.model.enumeration.Roles;
import cp.casopractico.security.domain.persistence.RolRepository;
import cp.casopractico.security.domain.persistence.UsuarioRepository;
import cp.casopractico.security.domain.service.UsuarioService;
import cp.casopractico.security.mapping.UsuarioMapper;
import cp.casopractico.security.middleware.JwtHandler;
import cp.casopractico.security.middleware.UserDetailsImpl;
import cp.casopractico.security.service.validator.RegisterUsuarioValidator;
import cp.casopractico.shared.api.ApiController;
import cp.casopractico.shared.application.Notification;
import cp.casopractico.shared.application.Result;
import cp.casopractico.shared.mapping.EnhancedModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    private static final Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    RolRepository rolRepository;
    @Autowired
    RegisterUsuarioValidator registerUsuarioValidator;
    @Autowired
    UsuarioMapper usuarioMapper;
    @Autowired
    EnhancedModelMapper enhancedModelMapper;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtHandler handler;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public Page<UsuarioDto> getAll(Pageable pageable) {
        return usuarioMapper.modelListToPage(usuarioRepository.findAll(), pageable);
    }

    @Override
    public UsuarioDto getById(Long userId) {
        return usuarioMapper.toResource(usuarioRepository.getReferenceById(userId));
    }

    @Override
    public ResponseEntity<?> authenticate(AuthenticateRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = handler.generateToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        AuthenticateResource resource = enhancedModelMapper.map(userDetails, AuthenticateResource.class);
        resource.setToken(token);
        resource.setRoles(roles);

        return ApiController.ok(resource);
    }

    @Override
    public Result<UsuarioDto, Notification> register(RegisterUsuarioRequest request) {

        Notification notification = this.registerUsuarioValidator.validate(request);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }

        Set<String> rolesStringSet = request.getRoles();
        Set<Rol> roles = new HashSet<>();

        if (rolesStringSet == null) {
            rolRepository.findByNombre(Roles.ROLE_CLIENTE)
                    .map(roles::add)
                    .orElseThrow(() -> new RuntimeException("Role not found"));
        } else {
            rolesStringSet.forEach(roleString ->
                    rolRepository.findByNombre(Roles.valueOf(roleString))
                            .map(roles::add)
                            .orElseThrow(() -> new RuntimeException("Role not found.")));
        }

        Usuario usuario = new Usuario(
                request.getUsername(),
                request.getEmail(),
                encoder.encode(request.getPassword()),
                request.getName(),
                request.getLastName(),
                roles
        );

        usuarioRepository.save(usuario);
        UsuarioDto resource = usuarioMapper.toResource(usuario);
        return Result.success(resource);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with username: %s", username)));
        return UserDetailsImpl.build(user);
    }
}

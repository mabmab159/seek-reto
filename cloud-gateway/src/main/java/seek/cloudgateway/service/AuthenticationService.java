package seek.cloudgateway.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import seek.cloudgateway.config.JwtService;
import seek.cloudgateway.model.entity.UserEntity;
import seek.cloudgateway.model.request.AuthenticationRequest;
import seek.cloudgateway.model.request.RegisterRequest;
import seek.cloudgateway.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public String authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        UserEntity userEntity = userRepository.findByUsername(request.username())
                .orElse(null);
        if (userEntity == null) {
            throw new UsernameNotFoundException("Usuario no registrado en BD");
        }
        return jwtService.generateToken(userEntity);
    }

    public String register(RegisterRequest request) {
        UserEntity userEntity = UserEntity.builder()
                .name(request.name())
                .username(request.username())
                .lastname(request.lastname())
                .email(request.email())
                .rol(request.rol())
                .password(passwordEncoder.encode(request.password()))
                .build();
        userRepository.save(userEntity);
        return jwtService.generateToken(userEntity);
    }
}

package caps.tf.service.auth;

import caps.tf.domain.user.ERole;
import caps.tf.domain.user.User;
import caps.tf.dto.auth.AuthSignUpDto;
import caps.tf.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void signUp(AuthSignUpDto authSignUpDto){
        userRepository.save(
                User.builder()
                    .serialId(authSignUpDto.serialId())
                    .password(bCryptPasswordEncoder.encode(authSignUpDto.password()))
                    .role(ERole.ADMIN)
                    .build()
        );
    }
}

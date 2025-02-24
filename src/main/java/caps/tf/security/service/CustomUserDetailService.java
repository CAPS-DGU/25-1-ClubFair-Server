package caps.tf.security.service;

import caps.tf.exception.CommonException;
import caps.tf.exception.UserErrorCode;
import caps.tf.repository.UserRepository;
import caps.tf.repository.UserSecurityForm;
import caps.tf.security.info.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserPrincipal loadUserByUsername(String username) throws UsernameNotFoundException {
        UserSecurityForm userSecurityForm = userRepository.findUserSecurityFromBySerialId(username)
                .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 아이디입니다."));
        log.info(("아이디 기반 조회 성공"));

        return UserPrincipal.create(userSecurityForm);
    }

    public UserPrincipal loadUserById(UUID id) {
        UserSecurityForm userSecurityForm = userRepository.findUserSecurityFromById(id)
                .orElseThrow(() -> CommonException.type(UserErrorCode.NOT_FOUND_USER));
        log.info("user id 기반 조회 성공");

        return UserPrincipal.create(userSecurityForm);
    }
}

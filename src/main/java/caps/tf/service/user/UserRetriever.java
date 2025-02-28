package caps.tf.service.user;

import caps.tf.domain.user.User;
import caps.tf.exception.CommonException;
import caps.tf.exception.UserErrorCode;
import caps.tf.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserRetriever {
    private final UserRepository userRepository;

    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        CommonException.type(UserErrorCode.NOT_FOUND_USER)
                );
    }
}

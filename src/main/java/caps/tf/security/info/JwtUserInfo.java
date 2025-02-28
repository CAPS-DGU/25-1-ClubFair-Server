package caps.tf.security.info;

import caps.tf.domain.user.ERole;

import java.util.UUID;

public record JwtUserInfo(UUID userId, ERole role) {
}

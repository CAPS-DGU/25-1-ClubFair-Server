package caps.tf.repository;

import caps.tf.domain.user.ERole;
import caps.tf.domain.user.User;

import java.util.UUID;

public interface UserSecurityForm {
    UUID getId();
    ERole getRole();
    String getPassword();

    static UserSecurityForm invoke(User user){
        return new UserSecurityForm() {
            @Override
            public UUID getId() {
                return user.getId();
            }

            @Override
            public String getPassword() {
                return user.getPassword();
            }

            @Override
            public ERole getRole() {
                return user.getRole();
            }
        };
    }
}

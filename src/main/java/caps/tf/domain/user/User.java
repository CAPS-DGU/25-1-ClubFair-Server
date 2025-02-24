package caps.tf.domain.user;

import caps.tf.domain.base.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "serial_id", nullable = false, unique = true)
    private String serialId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private ERole role;

    @Builder
    public User(
            final String serialId,
            final String password,
            final ERole role
    ) {
        this.serialId = serialId;
        this.password = password;
        this.role = role;
    }

    public static User from(
            final String serialId,
            final String password,
            final ERole eRole
    ) {
        return User.builder()
                   .serialId(serialId)
                   .password(password)
                   .role(eRole)
                   .build();
    }
}

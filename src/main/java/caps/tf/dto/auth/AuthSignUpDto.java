package caps.tf.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthSignUpDto(
        @JsonProperty(value = "id")
        String serialId,
        @JsonProperty(value = "password")
        String password
) {
}

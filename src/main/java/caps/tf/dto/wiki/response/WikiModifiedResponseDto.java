package caps.tf.dto.wiki.response;

import caps.tf.domain.wiki.Wiki;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.UUID;

@Builder
public record WikiModifiedResponseDto(
        @JsonProperty("id")
        UUID id,

        @JsonProperty("name")
        String name
) {
        public static WikiModifiedResponseDto from(
                final Wiki wiki
        ) {
                return WikiModifiedResponseDto
                        .builder()
                        .id(wiki.getId())
                        .name(wiki.getName())
                        .build();
        }
}

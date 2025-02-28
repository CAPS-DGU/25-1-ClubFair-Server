package caps.tf.dto.wiki.request;

import caps.tf.domain.wiki.EDepartment;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.Optional;

public record PatchWikiRequestDto(
        @JsonProperty("name")
        Optional<@NotBlank String> name,

        @JsonProperty("entranceYear")
        Optional<
                @Pattern(
                        regexp = "^[0-9]{2}$",
                        message = "두 자리 학번만 입력 가능합니다."
                )
                String
        > entranceYear,

        @JsonProperty("department")
        Optional<EDepartment> eDepartment,

        @JsonProperty("content")
        Optional<@NotBlank String> content,

        @JsonProperty("writer")
        Optional<@NotBlank String> writer
) {
}

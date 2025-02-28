package caps.tf.dto.wiki.request;

import caps.tf.domain.wiki.EDepartment;
import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateWikiRequestDto(
        @JsonProperty("name")
        String name,

        @JsonProperty("entranceYear")
        String entranceYear,

        @JsonProperty("department")
        EDepartment eDepartment,

        @JsonProperty("content")
        String content,

        @JsonProperty("writer")
        String writer
) {
}

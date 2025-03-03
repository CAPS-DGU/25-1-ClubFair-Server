package caps.tf.dto.wiki.response;

import caps.tf.domain.wiki.Wiki;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record WikiResponseDto(
    @JsonProperty("id")
    UUID id,

    @JsonProperty("name")
    String name,

    @JsonProperty("entranceYear")
    String entranceYear,

    @JsonProperty("college")
    String college,

    @JsonProperty("department")
    String department,

    @JsonProperty("content")
    String content,

    @JsonProperty("writer")
    String writer,

    @JsonProperty("createdAt")
    LocalDateTime createdAt,

    @JsonProperty("modifiedAt")
    LocalDateTime modifiedAt
) {
        public static WikiResponseDto from(Wiki wiki) {
            return WikiResponseDto.builder()
                                  .id(wiki.getId())
                                  .name(wiki.getName())
                                  .entranceYear(wiki.getEntranceYear())
                                  .college(wiki.getECollege().getName())
                                  .department(wiki.getEDepartment().getName())
                                  .content(wiki.getContent())
                                  .writer(wiki.getWriter())
                                  .createdAt(wiki.getCreatedDate())
                                  .modifiedAt(wiki.getModifiedDate())
                                  .build();
        }
}

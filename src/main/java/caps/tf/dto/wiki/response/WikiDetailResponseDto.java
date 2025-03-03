package caps.tf.dto.wiki.response;

import caps.tf.domain.wiki.Wiki;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record WikiDetailResponseDto(
        @JsonProperty("errorCode") String errorCode,
        @JsonProperty("message") String message,
        @JsonProperty("result") Result result
) {
    @Builder
    public record Result(
            @JsonProperty("id") UUID id,
            @JsonProperty("name") String name,
            @JsonProperty("entranceYear") String entranceYear,
            @JsonProperty("college") String college,
            @JsonProperty("department") String department,
            @JsonProperty("content") String content,
            @JsonProperty("writer") String writer,
            @JsonProperty("createdAt") LocalDateTime createdAt,
            @JsonProperty("modifiedAt") LocalDateTime modifiedAt
    ) {
        // Wiki -> Result 변환
        public static Result of(Wiki wiki) {
            return Result.builder()
                    .id(wiki.getId())
                    .entranceYear(wiki.getEntranceYear() != null ? wiki.getEntranceYear().substring(0, 2) : null)
                    .entranceYear(wiki.getEntranceYear())
                    .college(wiki.getECollege() != null ? wiki.getECollege().getName() : null)
                    .department(wiki.getEDepartment() != null ? wiki.getEDepartment().getName() : null)
                    .content(wiki.getContent())
                    .writer(wiki.getWriter())
                    .createdAt(wiki.getCreatedAt())
                    .modifiedAt(wiki.getModifiedAt())
                    .build();
        }
    }

    public static WikiDetailResponseDto of(Wiki wiki) {
        return WikiDetailResponseDto.builder()
                .errorCode(null)
                .message("SUCCESS")
                .result(Result.of(wiki))
                .build();
    }
}

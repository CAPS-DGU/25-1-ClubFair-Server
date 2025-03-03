package caps.tf.dto.wiki.response;

import caps.tf.domain.wiki.Wiki;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
public record WikiListResponseDto(
        @JsonProperty("errorCode") String errorCode,
        @JsonProperty("message") String message,
        @JsonProperty("result") WikiListResult result

) {
    @Builder
    public record WikiListResult(
            @JsonProperty("totalPage") int totalPage,
            @JsonProperty("totalElement") long totalElement,
            @JsonProperty("wikiList") List<WikiInfo> wikiList
    ) {
    }
    @Builder
    public record WikiInfo(
            @JsonProperty("id") UUID id,
            @JsonProperty("name") String name,
            @JsonProperty("entranceYear") String entranceYear,
            @JsonProperty("college") String college,
            @JsonProperty("department") String department,
            @JsonProperty("createdAt") LocalDateTime createdAt,
            @JsonProperty("modifiedAt") LocalDateTime modifiedAt
    ) {
        public static WikiInfo of(Wiki wiki) {
            return WikiInfo.builder()
                    .id(wiki.getId())
                    .name(wiki.getName())
                    .entranceYear(wiki.getEntranceYear())
                    .college(wiki.getEDepartment().getECollege().getName())
                    .department(wiki.getEDepartment().getName())
                    .createdAt(wiki.getCreatedAt())
                    .modifiedAt(wiki.getModifiedAt())
                    .build();
        }
    }
}
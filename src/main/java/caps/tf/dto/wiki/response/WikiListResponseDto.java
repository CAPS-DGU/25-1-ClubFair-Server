package caps.tf.dto.wiki.response;

import caps.tf.domain.wiki.Wiki;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record WikiListResponseDto(
        @JsonProperty("wikiList") List<WikiInfo> wikiList,
        @JsonProperty("nextLastWikiId") UUID nextLastWikiId
) {
    @Builder
    public record WikiInfo(
            @JsonProperty("name") String name,
            @JsonProperty("department") String department,
            @JsonProperty("college") String college
    ) {
        public static WikiInfo of(Wiki wiki) {
            return WikiInfo.builder()
                    .name(wiki.getName())
                    .department(wiki.getEDepartment().getName())
                    .college(wiki.getEDepartment().getECollege().getName())
                    .build();
        }
    }
}
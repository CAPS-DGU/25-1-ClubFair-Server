package caps.tf.dto.wiki.response;

import caps.tf.domain.wiki.ECollege;
import caps.tf.domain.wiki.EDepartment;
import com.fasterxml.jackson.annotation.JsonProperty;
import caps.tf.domain.wiki.Wiki;
import lombok.Builder;

@Builder
public record WikiDetailResponseDto(
        @JsonProperty("name")
        String name,

        @JsonProperty("entranceYear")
        String entranceYear,

        @JsonProperty("college")
        ECollege college,

        @JsonProperty("department")
        EDepartment eDepartment,

        @JsonProperty("content")
        String content
) {
    public static WikiDetailResponseDto of(Wiki wiki) {
        return WikiDetailResponseDto.builder()
                .name(wiki.getName())
                .entranceYear(wiki.getEntranceYear())
                .college(wiki.getCollege())
                .eDepartment(wiki.getEDepartment())
                .content(wiki.getContent())
                .build();
    }
}
package caps.tf.dto.wiki.response;

import caps.tf.domain.wiki.EDepartment;
import com.fasterxml.jackson.annotation.JsonProperty;
import caps.tf.domain.wiki.Wiki;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record WikiListResponseDto(
        @JsonProperty("name")
        String name,

        @JsonProperty("entranceYear")
        String entranceYear,

        @JsonProperty("department")
        EDepartment eDepartment
) {
    public static WikiDetailResponseDto of(
            Wiki wiki
    ) {
        return WikiDetailResponseDto.builder()
                .name(wiki.getName())
                .entranceYear(wiki.getEntranceYear())
                .eDepartment(wiki.getEDepartment())
                .build();
    }
}
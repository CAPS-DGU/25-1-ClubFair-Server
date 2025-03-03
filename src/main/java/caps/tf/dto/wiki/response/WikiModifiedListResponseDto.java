package caps.tf.dto.wiki.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public record WikiModifiedListResponseDto(
        @JsonProperty("wikiModifiedList")
        List<WikiModifiedResponseDto> wikiModifiedResponseDtoList
) {
    public static WikiModifiedListResponseDto from(
            List<WikiModifiedResponseDto> wikiModifiedResponseDtoList
    ) {
        return WikiModifiedListResponseDto
                .builder()
                .wikiModifiedResponseDtoList(wikiModifiedResponseDtoList)
                .build();
    }
}

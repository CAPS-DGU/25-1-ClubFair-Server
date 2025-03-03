package caps.tf.dto.wiki.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public record WikiListResponseDto(
        @JsonProperty("totalPage") int totalPage,
        @JsonProperty("totalElement") Long totalElement,
        @JsonProperty("wikiList") List<WikiResponseDto> wikiList
) {
    public static WikiListResponseDto of(
            final int totalPage,
            final Long totalElement,
            final List<WikiResponseDto> wikiResponseDtoList
    ) {
        return WikiListResponseDto.builder()
                .wikiList(wikiResponseDtoList)
                .totalPage(totalPage)
                .totalElement(totalElement)
                .build();
    }
}
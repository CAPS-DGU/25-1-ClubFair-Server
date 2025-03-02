package caps.tf.service.wiki;

import caps.tf.domain.user.User;
import caps.tf.domain.wiki.Wiki;
import caps.tf.dto.wiki.request.CreateWikiRequestDto;
import caps.tf.dto.wiki.request.PatchWikiRequestDto;
import caps.tf.dto.wiki.response.WikiModifiedListResponseDto;
import caps.tf.dto.wiki.response.WikiModifiedResponseDto;
import caps.tf.exception.CommonException;
import caps.tf.exception.WikiErrorCode;
import caps.tf.service.user.UserRetriever;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WikiService {
    private final WikiSaver wikiSaver;
    private final WikiRetriever wikiRetriever;
    private final WikiRemover wikiRemover;
    private final UserRetriever userRetriever;

    @Transactional
    public URI createWiki(
            UUID userId,
            CreateWikiRequestDto createWikiRequestDto
    ) {
        User currentUser = userRetriever.getUserById(userId);

        // 유효성 검사 코드 추후 추가

        Wiki targetWiki
                = Wiki.of(
                        createWikiRequestDto.name(),
                        createWikiRequestDto.entranceYear(),
                        createWikiRequestDto.eDepartment(),
                        createWikiRequestDto.content(),
                        createWikiRequestDto.writer(),
                        currentUser
                );

        Wiki savedWiki = wikiSaver.save(targetWiki);

        return URI.create("/wiki/" + savedWiki.getId());
    }

    @Transactional
    public Void patchWiki(
            UUID wikiId,
            PatchWikiRequestDto patchWikiRequestDto
    ) {
        Wiki targetWiki = wikiRetriever.getWikiById(wikiId);

        patchWikiRequestDto.name().ifPresent(targetWiki::setName);
        patchWikiRequestDto.entranceYear().ifPresent(targetWiki::setEntranceYear);
        patchWikiRequestDto.eDepartment().ifPresent(targetWiki::setEDepartment);
        patchWikiRequestDto.content().ifPresent(targetWiki::setContent);
        patchWikiRequestDto.writer().ifPresent(targetWiki::setWriter);

        return null;
    }

    @Transactional
    public void deleteWiki(UUID wikiId) {
        if (!wikiRetriever.isWikiExist(wikiId))
            throw CommonException.type(WikiErrorCode.NOT_FOUND_WIKI);

        wikiRemover.deleteWikiById(wikiId);
    }

    public WikiModifiedListResponseDto getWikiModifiedList() {
        List<Wiki> wikiList = wikiRetriever.getWikiModifiedDescList();
        List<WikiModifiedResponseDto> wikiModifiedResponseDtoList
                = wikiList.stream()
                          .map(WikiModifiedResponseDto::from)
                          .toList();

        return WikiModifiedListResponseDto.from(
            wikiModifiedResponseDtoList
        );
    }
}

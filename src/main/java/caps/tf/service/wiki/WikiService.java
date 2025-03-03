package caps.tf.service.wiki;

import caps.tf.domain.user.User;
import caps.tf.domain.wiki.EDepartment;
import caps.tf.domain.wiki.Wiki;
import caps.tf.dto.wiki.request.CreateWikiRequestDto;
import caps.tf.dto.wiki.request.PatchWikiRequestDto;
import caps.tf.dto.wiki.response.WikiDetailResponseDto;
import caps.tf.dto.wiki.response.WikiListResponseDto;
import caps.tf.exception.CommonException;
import caps.tf.repository.WikiRepository;
import caps.tf.service.user.UserRetriever;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private final UserRetriever userRetriever;
    private final WikiRepository wikiRepository;

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
    public WikiListResponseDto getWikiList(
            int page,
            String name,
            EDepartment department
    ) {
        if (page < 1)
            throw CommonException.type(ChocoErrorCode.INVALID_PAGE_CHOCO);

        int size = 10;
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Wiki> WikiPage
                = WikiRetriever.findBy(box.getId(), pageable);

        if (page != 1 && page > WikiPage.getTotalPages())
            throw CommonException.type(ChocoErrorCode.INVALID_PAGE_CHOCO);
        List<WikiListResponseDto.WikiInfo> wikiInfoList = wikiList.stream()
                .map(WikiListResponseDto.WikiInfo::of)
                .toList();

        return WikiListResponseDto.builder()
                .errorCode(null)            // 성공 시 null
                .message("SUCCESS")         // 성공 시 "SUCCESS"
                .result(
                        WikiListResponseDto.WikiListResult.builder()
                                .totalPage(totalPages)
                                .totalElement(totalElements)
                                .wikiList(wikiInfoList)
                                .build()
                )
                .build();
    }

    @Transactional
    public WikiDetailResponseDto getWikiDetail(
            UUID wikiId
    ) {
        Wiki targetWiki = wikiRetriever.getWikiById(wikiId);

        return WikiDetailResponseDto.of(targetWiki);
    }
}
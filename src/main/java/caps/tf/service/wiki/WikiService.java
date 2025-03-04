package caps.tf.service.wiki;

import caps.tf.domain.user.User;
import caps.tf.domain.wiki.EDepartment;
import caps.tf.domain.wiki.Wiki;
import caps.tf.dto.wiki.request.CreateWikiRequestDto;
import caps.tf.dto.wiki.request.PatchWikiRequestDto;
import caps.tf.dto.wiki.response.WikiListResponseDto;
import caps.tf.dto.wiki.response.WikiResponseDto;
import caps.tf.exception.CommonException;
import caps.tf.exception.WikiErrorCode;
import caps.tf.repository.WikiRepository;
import caps.tf.dto.wiki.response.RandomWikiResponseDto;
import caps.tf.dto.wiki.response.WikiModifiedListResponseDto;
import caps.tf.dto.wiki.response.WikiModifiedResponseDto;
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
    private final WikiRemover wikiRemover;
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
            String department
    ) {
        if (page < 1)
            throw CommonException.type(WikiErrorCode.INVALID_PAGE_WIKI);

        Pageable pageable = PageRequest.of(page - 1, 20);
        Page<Wiki> wikiPage;
        if (!name.isEmpty())
            wikiPage = wikiRetriever.getWikiListByName(name, pageable);
        else if(department != null) {
            EDepartment eDepartment = EDepartment.fromDepartment(department);
            wikiPage = wikiRetriever.getWikiListByDepartment(eDepartment.name(), pageable);
        } else
            wikiPage = wikiRetriever.getWikiList(pageable);

        if (page != 1 && page > wikiPage.getTotalPages())
            throw CommonException.type(WikiErrorCode.INVALID_PAGE_WIKI);

        List<WikiResponseDto> wikiResponseDtoList = wikiPage.getContent()
                .stream()
                .map(WikiResponseDto::from)
                .toList();

        return WikiListResponseDto.of(
                wikiPage.getTotalPages(),
                wikiPage.getTotalElements(),
                wikiResponseDtoList
        );
    }

    @Transactional
    public WikiResponseDto getWikiDetail(
            UUID wikiId
    ) {
        Wiki targetWiki = wikiRetriever.getWikiById(wikiId);

        return WikiResponseDto.from(targetWiki);
    }

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

    public RandomWikiResponseDto getRandomWiki() {
        Wiki randomWiki = wikiRetriever.getRandomWiki();

        return RandomWikiResponseDto.from(randomWiki);
    }
}

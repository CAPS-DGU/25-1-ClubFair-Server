package caps.tf.service.wiki;

import caps.tf.domain.user.User;
import caps.tf.domain.wiki.EDepartment;
import caps.tf.domain.wiki.Wiki;
import caps.tf.dto.wiki.request.CreateWikiRequestDto;
import caps.tf.dto.wiki.request.PatchWikiRequestDto;
import caps.tf.dto.wiki.response.WikiDetailResponseDto;
import caps.tf.dto.wiki.response.WikiListResponseDto;
import caps.tf.repository.WikiRepository;
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
            UUID lastWikiId,
            int size,
            String name,
            String department
    ) {
        EDepartment eDepartment = department != null ? EDepartment.fromDepartment(department) : null;
        List<Wiki> wikiList = wikiRepository.findWikiList(lastWikiId, size, name, eDepartment);

        List<WikiListResponseDto.WikiInfo> wikiInfoList = wikiList.stream()
                .map(WikiListResponseDto.WikiInfo::of)
                .toList();

        UUID nextLastWikiId = wikiList.isEmpty() ? null : wikiList.get(wikiList.size() - 1).getId();

        return new WikiListResponseDto(wikiInfoList, nextLastWikiId);
    }

    @Transactional
    public WikiDetailResponseDto getWikiDetail(
            UUID wikiId
    ) {
        Wiki targetWiki = wikiRetriever.getWikiById(wikiId);

        return WikiDetailResponseDto.of(targetWiki);
    }
}
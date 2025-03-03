package caps.tf.service.wiki;

import caps.tf.domain.wiki.Wiki;
import caps.tf.exception.CommonException;
import caps.tf.exception.WikiErrorCode;
import caps.tf.repository.WikiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class WikiRetriever {
    private final WikiRepository wikiRepository;

    public Wiki getWikiById(UUID id) {
        return wikiRepository.findById(id)
                .orElseThrow(() ->
                        CommonException.type(WikiErrorCode.NOT_FOUND_WIKI)
                );
    }

    public Page<Wiki> getWikiListByName(String targetName, Pageable pageable) {
        return wikiRepository.findAllByName(targetName, pageable);
    }

    public Page<Wiki> getWikiListByDepartment(String departmentName, Pageable pageable) {
        return wikiRepository.findAllByEDepartment(departmentName, pageable);
    }
}
package caps.tf.service.wiki;

import caps.tf.domain.wiki.Wiki;
import caps.tf.exception.CommonException;
import caps.tf.exception.WikiErrorCode;
import caps.tf.repository.WikiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
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

    public Boolean isWikiExist(UUID id) {
        return wikiRepository.existsById(id);
    }

    public List<Wiki> getWikiModifiedDescList() {
        return wikiRepository.findTop7ByOrderByModifiedDateDesc();
    }
}

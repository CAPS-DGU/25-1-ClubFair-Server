package caps.tf.service.wiki;

import caps.tf.repository.WikiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class WikiRemover {
    private final WikiRepository wikiRepository;

    public void deleteWikiById(UUID wikiId) {
        wikiRepository.deleteById(wikiId);
    }
}

package caps.tf.service.wiki;

import caps.tf.domain.wiki.Wiki;
import caps.tf.repository.WikiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WikiSaver {
   private final WikiRepository wikiRepository;

   public Wiki save(Wiki wiki) {
       return wikiRepository.save(wiki);
   }
}

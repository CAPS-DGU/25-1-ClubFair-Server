package caps.tf.repository;

import caps.tf.domain.wiki.Wiki;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WikiRepository extends JpaRepository<Wiki, UUID> {
}

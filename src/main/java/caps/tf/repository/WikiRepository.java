package caps.tf.repository;

import caps.tf.domain.wiki.Wiki;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.UUID;

public interface WikiRepository extends JpaRepository<Wiki, UUID> {
    boolean existsById(@NonNull UUID id);
    List<Wiki> findTop7ByOrderByModifiedDateDesc();
    @Query(
            value = "SELECT * FROM wiki ORDER BY RAND() LIMIT 1",
            nativeQuery = true
    )
    Wiki findByRandom();
}

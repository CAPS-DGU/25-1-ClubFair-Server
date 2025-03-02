package caps.tf.repository;

import caps.tf.domain.wiki.EDepartment;
import caps.tf.domain.wiki.Wiki;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface WikiRepository extends JpaRepository<Wiki, UUID> {

    @Query("SELECT w FROM Wiki w WHERE (:lastWikiId IS NULL OR w.id < :lastWikiId) " +
            "AND (:name IS NULL OR w.name LIKE %:name%) " +
            "AND (:department IS NULL OR w.department = :department) " +
            "ORDER BY w.id DESC")

    List<Wiki> findWikiList(@Param("lastWikiId") UUID lastWikiId,
                            @Param("size") int size,
                            @Param("name") String name,
                            @Param("department") EDepartment department);
}
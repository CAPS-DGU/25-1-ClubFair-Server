package caps.tf.repository;

import caps.tf.domain.wiki.EDepartment;
import caps.tf.domain.wiki.Wiki;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;
import java.util.UUID;

public interface WikiRepository extends JpaRepository<Wiki, UUID> {

    @Query("SELECT w FROM Wiki w " +
            "WHERE (:name IS NULL OR w.name LIKE %:name%) " +
            "AND (:department IS NULL OR w.eDepartment = :department) " +
            "ORDER BY w.createdAt DESC " +
            "OFFSET :offset ROWS FETCH NEXT :size ROWS ONLY")
    List<Wiki> findWikiList(@Param("offset") int offset,
                            @Param("size") int size,
                            @Param("name") String name,
                            @Param("department") EDepartment department);

    @Query("SELECT COUNT(w) FROM Wiki w " +
            "WHERE (:name IS NULL OR w.name LIKE %:name%) " +
            "AND (:department IS NULL OR w.eDepartment = :department)")
    long countWikiList(@Param("name") String name, @Param("department") String department);
}
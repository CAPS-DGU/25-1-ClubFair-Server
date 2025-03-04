package caps.tf.repository;

import caps.tf.domain.wiki.Wiki;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
  
    @Query(
            value = "SELECT * " +
                    "FROM wiki w " +
                    "WHERE w.name = :name",
            countQuery = "SELECT COUNT(*) " +
                         "FROM wiki w " +
                         "WHERE w.name = :name",
            nativeQuery = true
    )
    Page<Wiki> findAllByName(String name, Pageable pageable);

    @Query(
            value = "SELECT * " +
                    "FROM wiki w " +
                    "WHERE w.department = :department",
            countQuery = "SELECT COUNT(*) " +
                    "FROM wiki w " +
                    "WHERE w.department = :department",
            nativeQuery = true
    )
    Page<Wiki> findAllByEDepartment(String department, Pageable pageable);

    @Query(
            value = "SELECT * " +
                    "FROM wiki w",
            countQuery = "SELECT COUNT(*) " +
                         "FROM wiki w",
            nativeQuery = true
    )
    Page<Wiki> findAllByPagable(Pageable pageable);
}

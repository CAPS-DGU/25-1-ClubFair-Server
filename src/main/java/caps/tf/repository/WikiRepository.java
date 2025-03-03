package caps.tf.repository;

import caps.tf.domain.wiki.Wiki;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface WikiRepository extends JpaRepository<Wiki, UUID> {
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
}
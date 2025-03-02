package caps.tf.domain.wiki;

import caps.tf.domain.base.BaseTimeEntity;
import caps.tf.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Setter
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "wiki")
public class Wiki extends BaseTimeEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "entrance_year", length = 2, nullable = false)
    private String entranceYear;

    @Column(name = "department", nullable = false)
    @Enumerated(EnumType.STRING)
    private EDepartment eDepartment;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "writer", nullable = false)
    private String writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public ECollege getCollege() {
        return this.eDepartment != null ? this.eDepartment.getECollege() : null;
    }

    @Builder
    public Wiki(
            final String name,
            final String entranceYear,
            final EDepartment eDepartment,
            final String content,
            final String writer,
            final User user
    ) {
        this.name = name;
        this.entranceYear = entranceYear;
        this.eDepartment = eDepartment;
        this.content = content;
        this.writer = writer;
        this.user = user;
    }

    public static Wiki of(
            final String name,
            final String entranceYear,
            final EDepartment eDepartment,
            final String content,
            final String writer,
            final User user
    ) {
        return Wiki.builder()
                   .name(name)
                   .entranceYear(entranceYear)
                   .eDepartment(eDepartment)
                   .content(content)
                   .writer(writer)
                   .user(user)
                   .build();
    }
}

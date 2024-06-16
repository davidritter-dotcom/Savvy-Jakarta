package ch.ffhs.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "diaryentry")
public class DiaryEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long entryId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = true)
    private String content;

    @Column(nullable = false)
    private Long user_id;
}

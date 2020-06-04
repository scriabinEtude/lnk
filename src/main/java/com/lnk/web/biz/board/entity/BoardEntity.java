package com.lnk.web.biz.board.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

//파라미터가 없는 기본 생성자를 추가하는 어노테이션(JPA 사용을 위해 기본 생성자 생성 필수)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
/* lombok의 @Data를 사용하면 Getter, Setter, toString 등을 한번에 해결할 수 있지만
   Setter 어노테이션은 안정성을 보장받기 어려우므로 Builder 을 사용*/
@Getter
@Entity
@Table(name = "board")
public class BoardEntity extends TimeEntity{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, nullable = false)
    private String writer;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(columnDefinition = "TEXT")
    private String filePath;

    @Builder
    public BoardEntity(Long id, String title, String content, String writer, String filePath) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.filePath = filePath;
    }
}

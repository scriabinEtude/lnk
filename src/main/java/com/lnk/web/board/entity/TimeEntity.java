package com.lnk.web.board.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
//테이블로 매핑하지 않고, 자식 클래스(BoardENtity)에게 매핑정보를 상속하기 위한 어노테이션
@MappedSuperclass
//JPA에게 해당 Entity는 Auditing기능을 사용한다는 것을 알리는 어노테이션입
@EntityListeners(AuditingEntityListener.class)
public abstract class TimeEntity {
    /* Entity가 처음 저장될때 생성일을 주입하는 어노테이션
       이때 생성일은 update할 필요가 없으므로, updatable = false 속성 추가
       속성을 추가하지 않으면 수정 시, 해당 값은 null이 되어버린다. */
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    // Entity가 수정될때 수정일자를 주입하는 어노테이션
    @LastModifiedDate
    private LocalDateTime modifiedDate;
}

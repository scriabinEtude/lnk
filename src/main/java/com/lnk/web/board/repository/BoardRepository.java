package com.lnk.web.board.repository;

import com.lnk.web.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    List<BoardEntity> findByTitleContaining(String keyword);
    //검색 관련 메소드 > https://www.baeldung.com/spring-jpa-like-queries
}

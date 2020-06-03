package com.lnk.web.board.controller;

import com.lnk.web.board.dto.BoardDto;
import com.lnk.web.board.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
/* AllArgsConstructor : 생성자로 Bean 객체를 받는 방식을 해결해주는 어노테이션
   예를 들어 Service객체를 주입 받을 때 @Autowired 같은 어노테이션을 부여하지 않아도 됨 */
@AllArgsConstructor
public class BoardController {
    private BoardService boardService;

    @GetMapping("/list")
    public String list(Model model){
        List<BoardDto> boardList = boardService.getBoardlist();
        model.addAttribute("boardList", boardList);
        return "thymeleaf/board/list";
    }

    @GetMapping("/write")
    public String write(){
        return "thymeleaf/board/write";
    }

    @PostMapping("/write")
    public String write(BoardDto boarddto){
        boardService.savePost(boarddto);
        return "redirect:/list";
    }

    @GetMapping("/post/{no}")
    public String view(@PathVariable("no") Long no, Model model) {
        BoardDto boardDTO = boardService.getPost(no);
        model.addAttribute("boardDto", boardDTO);
        return "thymeleaf/board/view";
    }

    @GetMapping("/post/edit/{no}")
    public String edit(@PathVariable("no") Long no, Model model) {
        BoardDto boardDTO = boardService.getPost(no);
        model.addAttribute("boardDto", boardDTO);
        return "thymeleaf/board/update";
    }

    //@PutMapping("/post/edit/{no}")
    @PostMapping("/post/edit/{no}")
    public String update(BoardDto boardDTO) {
        boardService.savePost(boardDTO);
        return "redirect:/list";
    }

    //@DeleteMapping("/post/{no}")
    @PostMapping("/post/{no}")
    public String delete(@PathVariable("no") Long no) {
        boardService.deletePost(no);
        return "redirect:/list";
    }
}

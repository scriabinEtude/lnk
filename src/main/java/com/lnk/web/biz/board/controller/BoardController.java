package com.lnk.web.biz.board.controller;

import com.lnk.web.biz.board.dto.BoardDto;
import com.lnk.web.biz.board.service.BoardService;
import com.lnk.web.cmm.config.S3Config;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
/* AllArgsConstructor : 생성자로 Bean 객체를 받는 방식을 해결해주는 어노테이션
   예를 들어 Service객체를 주입 받을 때 @Autowired 같은 어노테이션을 부여하지 않아도 됨 */
@AllArgsConstructor
public class BoardController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
    private S3Config s3Service;
    private BoardService boardService;

    @GetMapping("/list")
    public String list(Model model, @RequestParam(value="page", defaultValue = "1") Integer pageNum) {
        List<BoardDto> boardList = boardService.getBoardlist(pageNum);
        Integer[] pageList = boardService.getPageList(pageNum);
        model.addAttribute("boardList", boardList);
        model.addAttribute("pageList", pageList);

        return "thymeleaf/board/list";
    }

    @GetMapping("/write")
    public String write(){
        return "thymeleaf/board/write";
    }

    @PostMapping("/write")
    public String write(BoardDto boardDTO, MultipartFile file)throws IOException {
        if(!file.isEmpty()){
            String fileName = s3Service.upload(null, file);
            boardDTO.setFilePath(fileName);
        }
        boardService.savePost(boardDTO);
        return "redirect:/list";
    }

    @GetMapping("/post/{no}")
    public String view(@PathVariable("no") Long no, Model model) {
        BoardDto boardDTO = boardService.getPost(no);
        boardDTO.setImgFullPath("https://" + s3Service.CLOUD_FRONT_DOMAIN_NAME + "/" + boardDTO.getFilePath());

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
    public String update(BoardDto boardDTO, MultipartFile file)throws IOException {
        if(!file.isEmpty()){
            String fileName = s3Service.upload(null, file);
            boardDTO.setFilePath(fileName);
        }
        boardService.savePost(boardDTO);
        return "redirect:/list";
    }

    //@DeleteMapping("/post/{no}")
    @PostMapping("/post/{no}")
    public String delete(@PathVariable("no") Long no) {
        boardService.deletePost(no);
        return "redirect:/list";
    }

    @GetMapping("/board/search")
    public String search(@RequestParam(value="keyword") String keyword, Model model) {
        List<BoardDto> boardDtoList = boardService.searchPosts(keyword);

        model.addAttribute("boardList", boardDtoList);

        return "thymeleaf/board/list";
    }
}

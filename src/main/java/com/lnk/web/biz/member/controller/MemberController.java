package com.lnk.web.biz.member.controller;

import com.lnk.web.biz.member.dto.MemberDto;
import com.lnk.web.biz.member.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class MemberController {
    private MemberService memberService;

    @GetMapping("/")
    public String index() {
        return "thymeleaf/member/login";
    }

    @GetMapping("/user/signup")
    public String dispSignup() {
        return "thymeleaf/member/signup";
    }

    @PostMapping("/user/signup")
    public String execSignup(MemberDto memberDto) {
        memberService.joinUser(memberDto);
        return "redirect:/user/login";
    }

    @GetMapping("/user/login")
    public String dispLogin() {
        return "thymeleaf/member/login";
    }

    @GetMapping("/user/login/result")
    public String dispLoginResult() {
        return "thymeleaf/main";
    }

    @GetMapping("/user/logout/result")
    public String dispLogout() {
        return "thymeleaf/member/logout";
    }

    @GetMapping("/user/denied")
    public String dispDenied() {
        return "thymeleaf/cmm/denied";
    }

    @GetMapping("/user/info")
    public String dispMyInfo() {
        return "thymeleaf/member/myinfo";
    }

    @GetMapping("/admin")
    public String dispAdmin() {
        return "thymeleaf/member/admin";
    }
}

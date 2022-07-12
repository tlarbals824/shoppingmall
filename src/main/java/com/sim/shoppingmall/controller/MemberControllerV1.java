package com.sim.shoppingmall.controller;


import com.sim.shoppingmall.domain.member.Member;
import com.sim.shoppingmall.domain.member.MemberDTO;
import com.sim.shoppingmall.service.MemberServiceV1;
import jdk.jfr.MemoryAddress;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.lang.management.LockInfo;


@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberControllerV1 {

    private final MemberServiceV1 memberServiceV1;



    @GetMapping(value = "/login")
    public String loginForm(){

        return "member/login";
    }

    @PostMapping(value = "/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpServletResponse response){
        Member member = memberServiceV1.login(memberDTO.getLoginId(), memberDTO.getPassword());
        if(member == null){
            log.warn("not exist memberId");
            return "member/login";
        }

        //로그인 성공
        log.info("login={}",member);

        //쿠기 설정
        Cookie idCookie = new Cookie("id",String.valueOf(member.getId()));
        response.addCookie(idCookie);
        return "redirect:/";
    }

    @GetMapping(value = "/logout")
    public String logout(HttpServletResponse response){
        Cookie cookie = new Cookie("id",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }


    @GetMapping(value = "/join")
    public String joinForm(Model model){
        model.addAttribute("member",new MemberDTO());
        return "member/join";
    }

    @PostMapping(value = "/join")
    public String join(@ModelAttribute("member")MemberDTO memberDTO){
        if(memberServiceV1.isExistLoginId(memberDTO.getLoginId())||memberServiceV1.isExistEmail(memberDTO.getEmail())){
            return "member/join"; //회원가입 실패
        }

        memberServiceV1.createUser(memberDTO);
        //회원가입 성공
        log.info("joinMember = {}",memberDTO);
        return "redirect:/";
    }

}

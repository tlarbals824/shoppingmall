package com.sim.shoppingmall.controller;


import com.sim.shoppingmall.domain.board.BoardDTO;
import com.sim.shoppingmall.domain.member.Member;
import com.sim.shoppingmall.domain.member.MemberDTO;
import com.sim.shoppingmall.service.BoardServiceV2;
import com.sim.shoppingmall.service.MemberServiceV1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {


    private final MemberServiceV1 memberServiceV1;
    private final BoardServiceV2 boardServiceV2;

    @PostConstruct
    public void init(){
        for(int i=1;i<=10;i++){
            Member user = memberServiceV1.createUser(new MemberDTO("test" + i, "test" + i, "test" + i));
            for(int j=1;j<10;j++){
                boardServiceV2.create(new BoardDTO("test"+i,"test"+i,user));
            }
        }
    }



//    @GetMapping(value = "/")
    public String home(){
        return "home";
    }

    @GetMapping(value = "/")
    public String homeLogin(@CookieValue(value = "id", required = false)Long id, Model model){
        if(id == null){
            return "home";
        }

        if(memberServiceV1.findOne(id) == null){
            return "home";
        }

        return "homeLogin";

    }

}

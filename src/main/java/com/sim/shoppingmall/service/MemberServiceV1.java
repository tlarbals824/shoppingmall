package com.sim.shoppingmall.service;


import com.sim.shoppingmall.domain.member.Member;
import com.sim.shoppingmall.domain.member.MemberDTO;
import com.sim.shoppingmall.domain.member.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceV1 {

    private final MemberJpaRepository memberJpaRepository;

    @Transactional
    public Member createUser(MemberDTO memberDTO){
        Member saveMember = memberJpaRepository.save(new Member(
                memberDTO.getEmail(), memberDTO.getLoginId(), memberDTO.getPassword()));

        return saveMember;
    }

    public Member login(String loginId,String password){
        if(isExistLoginId(loginId)&&isExistMemberPassword(loginId,password)){
            return memberJpaRepository.findByLoginId(loginId).get();
        }
        return null;
    }


    public boolean isExistLoginId(String loginId){
        return memberJpaRepository.findByLoginId(loginId).isPresent();
    }

    public boolean isExistEmail(String email){
        return memberJpaRepository.findByEmail(email).isPresent();
    }

    public boolean isExistMemberPassword(String loginId,String password){
        return memberJpaRepository.findByLoginId(loginId).get().getPassword().equals(password);
    }





    public List<Member> findAll(){
        return memberJpaRepository.findAll();
    }

    public Member findOne(Long id){
        return memberJpaRepository.findById(id).get();//id로 찾은 member가 null일때 처리 해야함
    }

}

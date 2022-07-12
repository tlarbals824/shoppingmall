package com.sim.shoppingmall.domain.member;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MemberDTO {

    private String email;
    private String loginId;
    private String password;

    public MemberDTO() {
    }

    public MemberDTO(String email, String loginId, String password) {
        this.email = email;
        this.loginId = loginId;
        this.password = password;
    }
}

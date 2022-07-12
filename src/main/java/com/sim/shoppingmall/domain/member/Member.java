package com.sim.shoppingmall.domain.member;


import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Member {

    @Id@GeneratedValue
    private Long id;

    private String email;
    private String loginId;
    private String password;
    private LocalDateTime joinDate;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    public Member() {
    }

    public Member(String email, String loginId, String password) {
        this.email = email;
        this.loginId = loginId;
        this.password = password;
        this.grade=Grade.USER;
        joinDate=LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", loginId='" + loginId + '\'' +
                ", password='" + password + '\'' +
                ", joinDate=" + joinDate +
                ", grade=" + grade +
                '}';
    }
}

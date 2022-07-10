package com.sim.shoppingmall.domain.board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
public class BoardDTO {

    private String title;
    private String content;

    public BoardDTO() {
    }

    public BoardDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }

}

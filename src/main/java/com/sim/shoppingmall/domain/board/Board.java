package com.sim.shoppingmall.domain.board;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
public class Board {

    @Id@GeneratedValue
    @Column(name = "board_id")
    private Long id;

    private int readCount;
    private String title;
    private String content;
    private LocalDateTime boardDate;

    public Board() {
    }

    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Board(String title, String content, LocalDateTime boardDate) {
        this.readCount=0;
        this.title = title;
        this.content = content;
        this.boardDate = boardDate;
    }

    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", readCount=" + readCount +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", boardDate=" + boardDate +
                '}';
    }

    public void update(BoardDTO newBoard){
        this.title=newBoard.getTitle();
        this.content=newBoard.getContent();
        this.boardDate=LocalDateTime.now();
    }

    public void increaseReadCount(){
        this.readCount++;
    }

    public static Board createBoard(String title,String content){
        Board board = new Board(title, content);
        board.setReadCount(0);
        board.setBoardDate(LocalDateTime.now());
        return board;
    }
}

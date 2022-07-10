package com.sim.shoppingmall.service;

import com.sim.shoppingmall.domain.board.Board;
import com.sim.shoppingmall.domain.board.BoardDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;



@SpringBootTest
@Transactional
public class BoardServiceV1Test {

    @Autowired
    private BoardServiceV1 boardServiceV1;


    /**
     * BoardSerivceV1
     * create()
     */
    @Test
    public void createTest() throws Exception {
        BoardDTO board = new BoardDTO("test","test");
        Board saveBoard = boardServiceV1.create(board);

        Board result = boardServiceV1.findOne(saveBoard.getId());

        Assertions.assertThat(result).isEqualTo(saveBoard);
    }

    /**
     * BoardServiceV1
     * read()
     */
    @Test
    public void readTest() throws Exception {
        BoardDTO board = new BoardDTO("test","test");
        Board saveBoard = boardServiceV1.create(board);

        Board readBoard = boardServiceV1.read(saveBoard.getId());

        Assertions.assertThat(readBoard.getReadCount()).isEqualTo(1);

    }

    /**
     * BoardServiceV1
     * update()
     */
    @Test
    public void updateTest() throws Exception {
        BoardDTO board = new BoardDTO("","");
        Board saveBoard = boardServiceV1.create(board);
        BoardDTO updateBoard = new BoardDTO("update","update");

        boardServiceV1.update(saveBoard.getId(),updateBoard);


        Assertions.assertThat(saveBoard.getTitle()).isEqualTo("update");
        Assertions.assertThat(saveBoard.getContent()).isEqualTo("update");
    }

    /**
     * BoardServiceV1
     *
     */
    @Test
    public void deleteTest() throws Exception {
        BoardDTO board = new BoardDTO("","");
        Board saveBoard = boardServiceV1.create(board);

        boardServiceV1.delete(saveBoard.getId());

        Assertions.assertThat(boardServiceV1.findOne(saveBoard.getId()).getId()).isNull();
    }


}
package com.sim.shoppingmall.service;


import com.sim.shoppingmall.domain.board.Board;
import com.sim.shoppingmall.domain.board.BoardDTO;
import com.sim.shoppingmall.domain.board.BoardJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardServiceV1 {

    private final BoardJpaRepository boardJpaRepository;

    //게시글 작성
    @Transactional
    public Board create(BoardDTO board){
        Board saveBoard = Board.createBoard(board.getTitle(),board.getContent());
//        Board saveBoard = new Board(board.getTitle(),board.getContent(),LocalDateTime.now());
        boardJpaRepository.save(saveBoard);
        return saveBoard;
    }

    //게시글 보기
    @Transactional
    public Board read(Long boardId) {
        Board board = findOne(boardId);
        board.increaseReadCount();
        return board;
    }

    //게시글 수정
    @Transactional
    public void update(Long boardId,BoardDTO board){
        Board findBoard = findOne(boardId);
        findBoard.update(board);
    }

    //게시글 삭제
    @Transactional
    public void delete(Long boardId){
        boardJpaRepository.deleteById(boardId);
    }

    //게시글 전체 조회
    public List<Board> findAll(){
        return boardJpaRepository.findAll();
    }

    //게시글 검색
    public Board findOne(Long boardId){
        return boardJpaRepository.findById(boardId)
                .orElse(new Board("", "", LocalDateTime.now()));
    }


    public Page<Board> getBoardList(Pageable pageable,int amount){
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page,amount,Sort.by("boardDate").descending());

        return boardJpaRepository.findAll(pageable);
    }
}

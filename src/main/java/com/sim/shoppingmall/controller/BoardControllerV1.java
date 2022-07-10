package com.sim.shoppingmall.controller;


import com.sim.shoppingmall.domain.board.Board;
import com.sim.shoppingmall.domain.board.BoardDTO;
import com.sim.shoppingmall.service.BoardServiceV1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;


@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/board")
public class BoardControllerV1 {

    private final BoardServiceV1 boardServiceV1;


    @PostConstruct
    public void init() throws InterruptedException{
        for (int i = 1; i <= 100 ; i++) {
            String test = "test" + i;
            boardServiceV1.create(new BoardDTO(test, test));
        }
    }

    /**
     * CRUD : Read(GET)
     * 메인 게시판, 전체 게시물 조회
     */
//    @GetMapping
    public String mainBoardPageV1(Model model){
        List<Board> list = boardServiceV1.findAll();
        model.addAttribute("list",list);
        return "board/v1/mainBoardPage";
    }

    /**
     * CRUD : Read(GET)
     * V1 : 메인 게시판, 전체 게시물 조회
     * V2 : 페이징 기능 구현
     */
    @GetMapping
    public String mainBoardPageV2(@PageableDefault Pageable pageable, Model model){
        int pageAmount=10;
        Page<Board> boardList = boardServiceV1.getBoardList(pageable,pageAmount);
        model.addAttribute("list",boardList);
        log.info("총 element 수 : {}, 전체 page 수 : {}, 페이지에 표시할 element 수 : {}, 현재 페이지 index : {}, 현재 페이지의 element 수 : {}",
                boardList.getTotalElements(), boardList.getTotalPages(), boardList.getSize(),
                boardList.getNumber(), boardList.getNumberOfElements());

        return "board/v1/mainBoardPage";
    }


    /**
     *  CRUD : Read(GET)
     *  한 게시물 조회
     */
    @GetMapping(value = "/{boardId}")
    public String boardPage(@PathVariable("boardId") Long boardId, Model model){
        Board board = boardServiceV1.read(boardId);
        model.addAttribute("board",board);
        return "/board/v1/boardPage";
    }


    @GetMapping(value = "/{boardId}/edit")
    public String boardEditPage(@PathVariable("boardId")Long boardId,Model model){
        Board findBoard = boardServiceV1.findOne(boardId);
        model.addAttribute("board",findBoard);
        return "/board/v1/boardEdit";
    }

    @PostMapping(value = "/{boardId}/edit")
    public String boardEdit(@PathVariable("boardId")Long boardId,
                                @ModelAttribute("board") BoardDTO boardDTO){
        boardServiceV1.update(boardId,boardDTO);
        log.info("board={}",boardDTO);
        return "redirect:/board/{boardId}";
    }



    @GetMapping(value = "/add")
    public String boardAddPage(Model model){
        model.addAttribute("board",new BoardDTO());
        return "/board/v1/boardAdd";
    }

    @PostMapping(value = "/add")
    public String boardAdd(@ModelAttribute("board")BoardDTO boardDTO){
        Board saveBoard = boardServiceV1.create(boardDTO);
        log.info("board={}",saveBoard);
        return "redirect:/board/"+saveBoard.getId();
    }



    @GetMapping(value = "/{boardId}/delete")
    public String boardDelete(@PathVariable("boardId")Long boardId){
        boardServiceV1.delete(boardId);
        return "redirect:/board";
    }
}


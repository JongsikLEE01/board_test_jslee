package com.jslee.board_test_jslee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jslee.board_test_jslee.dto.Board;
import com.jslee.board_test_jslee.service.BoardService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    /**
     * 목록
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/list")
    public String list(Model model) throws Exception{
        List<Board> boardList = boardService.list();

        model.addAttribute("boardList", boardList);
        return "/board/list";
    }
    
    /**
     * 조회
     * @param no
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/read")
    public String read(@RequestParam("no") int no, Model model)throws Exception {
        Board board = boardService.select(no);

        int views = boardService.views(no);
        board.setViews(views);

        model.addAttribute("board", board);
        return "/board/read";
    }
    
    // 게시 이동
    @GetMapping("/insert")
    public String insert() {
        return "/board/insert";
    }

    /**
     * 게시글 게시
     * @param board
     * @return
     * @throws Exception
     */
    @PostMapping("/insert")
    public String insertPro(Board board)throws Exception {
        int result = boardService.insert(board);
        
        if(result == 0)
            return "redirect:/board/insert";

        return "redirect:/board/list";
    }
    

    // 수정 이동
    @GetMapping("/update")
    public String update(@RequestParam("no") int no, Model model) throws Exception {
        Board board = boardService.select(no);

        model.addAttribute("board", board);
        return "/board/update";
    }
    
    /**
     * 게시글 수정
     * @param board
     * @return
     * @throws Exception
     */
    @PostMapping("/update")
    public String updatePro(Board board)throws Exception {
        int result = boardService.update(board);

        if(result == 0)
            return "redirect:/board/update";

        return "redirect:/board/list";
    }
    
    /**
     * 게시글 삭제
     * @param no
     * @return
     * @throws Exception
     */
    @PostMapping("/delete")
    public String delete(int no)throws Exception {
        int result = boardService.delete(no);

        if(result == 0)
            return "redirect:/board/update";

        return "redirect:/board/list";
    }
}

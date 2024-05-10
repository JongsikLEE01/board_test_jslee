package com.jslee.board_test_jslee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jslee.board_test_jslee.dto.Board;

@Mapper
public interface BoardMapper {
    // 게시판 목록
    public List<Board> list()throws Exception;
    // 조회
    public Board select(int no) throws Exception;
    // 게시
    public int insert(Board board)throws Exception;
    // 수정
    public int update(Board board)throws Exception;
    // 삭제
    public int delete(int no)throws Exception;
    // 조회수
    public int views(int no)throws Exception;
}
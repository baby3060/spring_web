package com.mvc.entity;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Board {
    private long boardNo;
    private String content;
    private String writer;

    public void setBoardNo(long boardNo) {
        this.boardNo = boardNo;
    }
    public long getBoardNo() {
        return this.boardNo;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getContent() {
        return this.content;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }
    public String getWriter() {
        return this.writer;
    }
    
}
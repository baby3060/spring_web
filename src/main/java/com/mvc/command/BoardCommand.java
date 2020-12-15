package com.mvc.command;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class BoardCommand {
    private String content;
    private String writer;

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
package com.mvc.command;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class SearchCommand {

    @DateTimeFormat(pattern = "yyyyMMddHH")
    private LocalDateTime from;
    
    @DateTimeFormat(pattern = "yyyyMMddHH")
    private LocalDateTime to;

    private String search;

    public void setSearch(String search) {
        this.search = search;
    }
    public String getSearch() {
        return search;
    }


    public LocalDateTime getFrom() {
        return this.from;
    }
    public LocalDateTime getTo() {
        return this.to;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }

}
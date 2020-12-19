package com.mvc.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class UserInfo {
    private int memberSeq;
    private String email;
    private String name;
}
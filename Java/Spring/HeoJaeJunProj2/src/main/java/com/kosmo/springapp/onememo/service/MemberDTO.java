package com.kosmo.springapp.onememo.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

	private String id;
    private String pwd;
    private String name;
    private String gender;
    private String inter;
    private String grade;
    private String self;
    private java.sql.Date regidate;

}

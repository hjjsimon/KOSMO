package com.kosmo.restapi.model;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Alias("usersDto")//별칭, 마이바티스.xml에서 별칭으로 쓰면됨, 안그러면 패키지부터 class명까지 써야함
public class UsersDto {
	
	private String username;
	private String password;
	private String name;
	private Date joindate;
}
